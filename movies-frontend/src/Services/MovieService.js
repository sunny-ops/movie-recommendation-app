import axios from "axios";

const REST_API_BASE_URL = "http://localhost:8080/api/ratings";

export const listReviews = () => {
  return axios.get(REST_API_BASE_URL + "/reviews/1");
};

// export const createEmployee = (employee) =>
//   axios.post(REST_API_BASE_URL, employee);

// export const getEmployee = (employeeId) =>
//   axios.get(REST_API_BASE_URL + "/" + employeeId);

// export const updateEmployee = (employeeId, employee) =>
//   axios.put(REST_API_BASE_URL + "/" + employeeId + "/update", employee);

// export const deleteEmployee = (employeeId) =>
//   axios.delete(REST_API_BASE_URL + "/" + employeeId + "/delete");
