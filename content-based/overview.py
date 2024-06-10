# Import libraries
from sklearn.feature_extraction.text import TfidfVectorizer
import pandas as pd
from sklearn.metrics.pairwise import linear_kernel

# Sample data
# Load Movies Metadata
data = pd.read_csv('movies_metadata.csv', low_memory=False)

# data = {
#     'overview': [
#         'The quick brown fox',
#         'The quick brown dog',
#         'The lazy dog'
#     ]
# }
metadata = pd.DataFrame(data)

# 1. Initialize TfidfVectorizer
tfidf = TfidfVectorizer(stop_words='english')

# Replace NaN with an empty string
metadata['overview'] = metadata['overview'].fillna('')

# Fit and transform the data
tfidf_matrix = tfidf.fit_transform(metadata['overview'])

print(tfidf_matrix.shape)
# 2. Compute the cosine similarity matrix
cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
print(cosine_sim.shape)
print(cosine_sim[1])

# 3. recommend 10 similar movies
#Construct a reverse map of indices and movie titles
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

print(get_recommendations('The Dark Knight Rises'))




# # Convert to dense array and view
# dense_tfidf_matrix = tfidf_matrix.toarray()
# print(dense_tfidf_matrix)

# # View feature names
# feature_names = tfidf.get_feature_names_out()
# print(feature_names)

# # Convert to DataFrame for easier inspection
# tfidf_df = pd.DataFrame(dense_tfidf_matrix, columns=feature_names)
# print(tfidf_df)
