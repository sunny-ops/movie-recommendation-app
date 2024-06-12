import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { listReviews } from "../Services/MovieService";

function ListMovieComponent() {
  const [movies, setMovies] = useState([]);
  const navigator = useNavigate();
  useEffect(() => {
    getAllMovies();
  }, []);

  function getAllMovies() {
    listReviews()
      .then((response) => {
        setMovies(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }

  function addNewMovie() {
    // navigator("/add-employee");
  }

  function updateMovie(id) {}

  function removeMovie(id) {}

  return (
    <div className="container">
      <h2 className="text-center mt-2">List of Movies</h2>
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
    </div>
  );
}

export default ListMovieComponent;
