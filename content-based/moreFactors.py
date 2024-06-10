# Import libraries
from sklearn.feature_extraction.text import TfidfVectorizer
import pandas as pd
from sklearn.metrics.pairwise import linear_kernel
import numpy as np
# Parse the stringified features into their corresponding python objects
from ast import literal_eval
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity




# Sample data
# Load Movies Metadata
metadata = pd.read_csv('/users/shi/Downloads/movieMeta/movies_metadata.csv', low_memory=False)
# metadata = pd.DataFrame(metadata)

# Load keywords and credits
credits = pd.read_csv('/users/shi/Downloads/movieMeta/credits.csv')
keywords = pd.read_csv('/users/shi/Downloads/movieMeta/keywords.csv')

# Remove rows with bad IDs.
metadata = metadata.drop([19730, 29503, 35587])


# Convert IDs to int. Required for merging
keywords['id'] = keywords['id'].astype('int')
credits['id'] = credits['id'].astype('int')
metadata['id'] = metadata['id'].astype('int')

# Merge keywords and credits into your main metadata dataframe
metadata = metadata.merge(credits, on='id')
metadata = metadata.merge(keywords, on='id')

print(metadata.head(2))


features = ['cast', 'crew', 'keywords', 'genres']
for feature in features:
    metadata[feature] = metadata[feature].apply(literal_eval)


def get_director(x):
    for i in x:
        if i['job'] == 'Director':
            return i['name']
    return np.nan

def get_list(x):
    if isinstance(x, list):
        names = [i['name'] for i in x]
        #Check if more than 3 elements exist. If yes, return only first three. If no, return entire list.
        if len(names) > 3:
            names = names[:3]
        return names

    #Return empty list in case of missing/malformed data
    return []

# Define new director, cast, genres and keywords features that are in a suitable form.
metadata['director'] = metadata['crew'].apply(get_director)

features = ['cast', 'keywords', 'genres']
for feature in features:
    metadata[feature] = metadata[feature].apply(get_list)

# Print the new features of the first 3 films
print(metadata[['title', 'cast', 'director', 'keywords', 'genres']].head(3))

# Function to convert all strings to lower case and strip names of spaces
def clean_data(x):
    if isinstance(x, list):
        return [str.lower(i.replace(" ", "")) for i in x]
    else:
        #Check if director exists. If not, return empty string
        if isinstance(x, str):
            return str.lower(x.replace(" ", ""))
        else:
            return ''

# Apply clean_data function to your features.
features = ['cast', 'keywords', 'director', 'genres']

for feature in features:
    metadata[feature] = metadata[feature].apply(clean_data)

def create_soup(x):
    return ' '.join(x['keywords']) + ' ' + ' '.join(x['cast']) + ' ' + x['director'] + ' ' + ' '.join(x['genres'])

# Create a new soup feature
metadata['soup'] = metadata.apply(create_soup, axis=1)

print(metadata[['soup']].head(2))
# 1. Initialize TfidfVectorizer
tfidf = TfidfVectorizer(stop_words='english')
count = CountVectorizer(stop_words='english')

# Replace NaN with an empty string
metadata['soup'] = metadata['soup'].fillna('')

# Fit and transform the data
tfidf_matrix = tfidf.fit_transform(metadata['soup'])
count_matrix = count.fit_transform(metadata['soup'])

print(tfidf_matrix.shape)
# 2. Compute the cosine similarity matrix
cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
cosine_sim2 = cosine_similarity(count_matrix, count_matrix)
print(cosine_sim.shape)
print(cosine_sim[1])

# 3. recommend 10 similar movies
#Construct a reverse map of indices and movie titles
metadata = metadata.reset_index()
indices = pd.Series(metadata.index, index=metadata['title']).drop_duplicates()
print(indices[:10])

# Function that takes in movie title as input and outputs most similar movies
def get_recommendations(title, cosine_sim=cosine_sim):
    # Get the index of the movie that matches the title
    idx = indices[title]

    # Get the pairwsie similarity scores of all movies with that movie
    sim_scores = list(enumerate(cosine_sim[idx]))

    # Sort the movies based on the similarity scores
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)

    # Get the scores of the 10 most similar movies
    sim_scores = sim_scores[1:11]

    # Get the movie indices
    movie_indices = [i[0] for i in sim_scores]

    # Return the top 10 most similar movies
    return metadata['title'].iloc[movie_indices]

print(get_recommendations('The Dark Knight Rises', cosine_sim2))

# recommendations = []

# for title in metadata['title']:
#     recommended_titles = get_recommendations(title)
#     recommendations.append({'title': title, 'recommendations': recommended_titles})

# recommendations_df = pd.DataFrame(recommendations)
# print(recommendations_df.head(3))
# recommendations_df.to_csv('movie_recommendations.csv', index=False)


