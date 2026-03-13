import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthContext.jsx";
import { getUserRecommendations } from "../api/recommendationApi.js";
import Navbar from "../components/Navbar.jsx";
import RecommendationCard from "../components/RecommendationCard.jsx";

export default function Recommendation() {

  const { user } = useContext(AuthContext);
  const [recommendations, setRecommendations] = useState([]);

  useEffect(() => {
    if (user) {
      getUserRecommendations(user.id)
        .then((res) => setRecommendations(res.data))
        .catch((err) => console.error(err));
    }
  }, [user]);

  return (
    <>
      <Navbar />

      <div className="min-h-screen bg-gradient-to-b from-black to-gray-900 p-10 text-white">

        <h2 className="text-4xl font-bold text-center mb-10 text-orange-500">
          Personalized Fitness Recommendations
        </h2>

        <div className="grid md:grid-cols-2 gap-6 max-w-5xl mx-auto">

          {recommendations.length === 0 && (
            <p className="text-gray-400">No recommendations yet</p>
          )}

          {recommendations.map((reco, idx) => (
            <RecommendationCard key={idx} reco={reco} />
          ))}

        </div>
      </div>
    </>
  );
}