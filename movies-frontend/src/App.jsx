import "./App.css";
import FooterComponent from "./Components/FooterComponent";
import HeaderComponent from "./Components/HeaderComponent";
import ListMovieComponent from "./Components/ListMovieComponent";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <>
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          {/* // http://localhost:3000 */}
          <Route path="/:userId" element={<ListMovieComponent />}></Route>
          {/* // http://localhost:3000/employees*/}
          <Route
            path="/reviews/:userId"
            element={<ListMovieComponent />}
          ></Route>
          <Route
            path="/recommends/:userId"
            element={<ListMovieComponent />}
          ></Route>
          {/* // http://localhost:3000/add-employee*/}
          {/* <Route path="/add-employee" element={<EmployeeComponent />}></Route> */}
          {/* // http://localhost:3000/edit-employee*/}
          {/* <Route
            path="/edit-employee/:id"
            element={<EmployeeComponent />}
          ></Route> */}
        </Routes>

        <FooterComponent />
      </BrowserRouter>
    </>
  );
}

export default App;
