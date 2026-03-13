import axiosInstance from "./axiosIntance";

export const generateRecommendation = (data) =>
  axiosInstance.post("/recommendation/generate", data);

export const getUserRecommendations = (userId) =>
  axiosInstance.get(`/recommendation/user/${userId}`);
