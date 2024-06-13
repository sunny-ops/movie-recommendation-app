import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { listReviews, listRecommends } from "../Services/MovieService";
import { useParams, useLocation } from "react-router-dom";

function ListMovieComponent() {
  const { userId } = useParams();
  const [movies, setMovies] = useState([]);
  const navigator = useNavigate();
  const [apiEndpoint, setApiEndpoint] = useState("");
  // let apiEndpoint;
  // useEffect(() => {
  //   // Determine API endpoint based on current path
  //   if (location.pathname.startsWith("/reviews")) {
  //     // setApiEndpoint("reviews");
  //     apiEndpoint = "reviews";
  //   } else if (location.pathname.startsWith("/recommends")) {
  //     // setApiEndpoint("recommends");
  //     apiEndpoint = "recommends";
  //   }
  //   getAllMovies(userId);
  // }, []);

  useEffect(() => {
    if (location.pathname.startsWith("/reviews")) {
      setApiEndpoint("reviews");
    } else if (location.pathname.startsWith("/recommends")) {
      setApiEndpoint("recommends");
    }
    getAllMovies(userId);
  }, [apiEndpoint]);

  function getAllMovies(userId) {
    console.log("apiEndpoint", apiEndpoint);
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

  // Determine the title based on the path
  const getTitle = () => {
    if (location.pathname.startsWith("/reviews")) {
      return "List of Reviewed Movies";
    } else if (location.pathname.startsWith("/recommends")) {
      return "List of Recommended Movies";
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
    } else {
      return "hehe";
    }
  };

  function addNewMovie() {
    // navigator("/add-employee");
  }

  function updateMovie(id) {}

  function removeMovie(id) {}

  return (
    <div className="container">
      <h2 className="text-center mt-2">{getTitle()}</h2>
      <button className="btn btn-primary mb-2" onClick={addNewMovie}>
        Add Reviews
      </button>
      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Movie Id</th>
            <th>Movie Title</th>
            <th>Language</th>
            <th>Rating</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {movies.map((movie) => (
            <tr key={movie.movieId}>
              <td>{movie.movieId}</td>
              <td>{movie.title}</td>
              <td>{movie.language}</td>
              <td>{movie.voteAverage}</td>
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
            </tr>
          ))}
        </tbody>
      </table>
      <button className="btn btn-primary mb-2" onClick={addNewMovie}>
        {getButtonValue()}
      </button>
    </div>
  );
}

export default ListMovieComponent;
