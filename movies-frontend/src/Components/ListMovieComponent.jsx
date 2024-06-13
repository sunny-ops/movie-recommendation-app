import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  listReviews,
  listRecommends,
  listRatings,
} from "../Services/MovieService";
import { useParams, useLocation } from "react-router-dom";

function ListMovieComponent() {
  const { userId } = useParams();
  const [movies, setMovies] = useState([]);
  const [ratings, setRatings] = useState([]);
  const navigator = useNavigate();
  const [apiEndpoint, setApiEndpoint] = useState("");

  useEffect(() => {
    if (location.pathname.startsWith("/reviews")) {
      setApiEndpoint("reviews");
    } else if (location.pathname.startsWith("/recommends")) {
      setApiEndpoint("recommends");
    }
    getAllMovies(userId); // 是一个异步
    getAllRatings(userId);
  }, [location.pathname]);

  useEffect(() => {
    getAllMovies(userId);
    getAllRatings(userId);
  }, [apiEndpoint, userId]);

  function getAllMovies(userId) {
    // console.log("apiEndpoint", apiEndpoint);
    if (apiEndpoint == "reviews") {
      listReviews(userId)
        .then((response) => {
          setMovies(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    } else if (apiEndpoint == "recommends") {
      listRecommends(userId)
        .then((response) => {
          setMovies(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }

  function getAllRatings(userId) {
    listRatings(userId)
      .then((response) => {
        setRatings(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  // Determine the title based on the path
  const getTitle = () => {
    if (location.pathname.startsWith("/reviews")) {
      return "List of Reviewed Movies";
    } else if (location.pathname.startsWith("/recommends")) {
      return "List of 10 Recommended Movies";
    } else {
      return "List of Movies";
    }
  };

  // Determine the title based on the path
  const getButtonValue = () => {
    // console.log(apiEndpoint);
    if (apiEndpoint == "reviews") {
      return "Recommend 10 Movies for me";
    } else if (apiEndpoint == "recommends") {
      return "Go Back to my Reviews";
    }
  };

  function navToRecommends(userId) {
    navigator(`/recommends/${userId}`);
  }

  function navToReviews(userId) {
    navigator(`/reviews/${userId}`);
  }

  function addNewMovie() {
    // navigator("/add-employee");
  }

  function updateMovie(id) {}

  function removeMovie(id) {}

  // Function to find rating for a given movieId
  // const findRating = (movieId) => {
  //   const rating = ratings.find((r) => r.movieId === movieId);
  //   return rating ? rating.rating : "N/A"; // Return 'N/A' if no rating found
  // };

  const findRating = (movieId) => {
    let rating, movie;
    if (apiEndpoint == "reviews") {
      rating = ratings.find((r) => r.movieId === movieId);
      return rating ? rating.rating : "N/A";
    } else {
      movie = movies.find((r) => r.movieId === movieId);
      return movie ? movie.voteAverage : "N/A";
    }
  };

  return (
    <div className="container">
      <h2 className="text-center mt-5 mb-5">{getTitle()}</h2>
      {/* <button className="btn btn-primary mb-2" onClick={addNewMovie}>
        Add Reviews
      </button> */}
      {apiEndpoint === "reviews" && (
        <button className="btn btn-primary mb-2" onClick={addNewMovie}>
          Add Reviews
        </button>
      )}
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Movie Id</th>
            <th>Movie Title</th>
            <th>Language</th>
            <th>Rating</th>
            {apiEndpoint === "reviews" && <th>Actions</th>}
          </tr>
        </thead>
        <tbody>
          {movies.map((movie) => (
            <tr key={movie.movieId}>
              <td>{movie.movieId}</td>
              <td>{movie.title}</td>
              <td>{movie.language}</td>
              {/* <td>{movie.voteAverage}</td> */}
              <td>{findRating(movie.movieId)}</td>
              {apiEndpoint ===
                "reviews" /* Conditionally render Actions column content */ && (
                <td>
                  <button
                    className="btn btn-info"
                    onClick={() => updateMovie(movie.movieId)}
                  >
                    Update
                  </button>
                  <button
                    className="btn btn-danger"
                    onClick={() => removeMovie(movie.movieId)}
                  >
                    Delete
                  </button>
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
      <button
        className="btn btn-primary mb-5"
        onClick={() =>
          apiEndpoint == "reviews"
            ? navToRecommends(userId)
            : navToReviews(userId)
        }
      >
        {getButtonValue()}
      </button>
    </div>
  );
}

export default ListMovieComponent;