import axiosInstance from "./axiosIntance";

export const createActivity = (data) =>
  axiosInstance.post("/activities/tasks", data);

export const getActivities = (userId) =>
  axiosInstance.get("/activities", {
    headers: { "X-User-ID": userId },
  });

export const deleteActivity = (id) => {
  return axiosInstance.delete(`/activities/delete/${id}`);
};
