import { useState, useContext, useEffect } from "react";
import { generateRecommendation } from "../api/recommendationApi.js";
import { TextField, Button, MenuItem } from "@mui/material";
import { createActivity, getActivities, deleteActivity } from "../api/activityApi.js";
import { AuthContext } from "../context/AuthContext.jsx";
import Navbar from "../components/Navbar.jsx";
import ActivityCard from "../components/ActivityCard.jsx";

export default function Activity() {

  const { user } = useContext(AuthContext);

  const [activities, setActivities] = useState([]);

  const [form, setForm] = useState({
    userId: user?.id || null,
    type: "RUNNING",
    duration: "",
    caloriesBurned: "",
    startTime: "",
  });

  const fetchActivities = async () => {
    if (user) {
      const res = await getActivities(user.id);
          console.log("Fetched activities:", res.data);

      setActivities(res.data);
    }
  };

  useEffect(() => {
    fetchActivities();
  }, [user]);

  
  const handleSubmit = async () => {
    try {
        const activityData = {
      ...form,
      userId: user.id,
      startTime: form.startTime || null,
      
    };

    const res = await createActivity(activityData);
      const activityId = res.data.id;

      await generateRecommendation({
        userId: user.id,
        activityId: activityId
      });

      fetchActivities();

      setForm({
        ...form,
        duration: "",
        caloriesBurned: "",
        startTime: ""
      });

    } catch (error) {
      console.error(error);
    }
  };


  const handleDelete = async (id) => {
    try {
      await deleteActivity(id);
      fetchActivities();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <Navbar />

      <div className="p-10 bg-gradient-to-b from-black to-gray-900 p-10 text-white min-h-screen text-white">

        <h2 className="text-4xl font-bold text-center mb-10 text-orange-500">
          Track Activity
        </h2>

        

        <div className="grid gap-4 max-w-md mb-10 mx-auto">

          <TextField
            select
            label="Activity Type"
            value={form.type}
            onChange={(e) => setForm({ ...form, type: e.target.value })}
          >
            {[
              "RUNNING","WALKING","CYCLING","SWIMMING",
              "YOGA","WEIGHT_TRAINING","HIIT","CARDIO",
              "STRETCHING","OTHER"
            ].map((type) => (
              <MenuItem key={type} value={type}>
                {type}
              </MenuItem>
            ))}
          </TextField>

          <TextField
            label="Duration (min)"
            type="number"
            value={form.duration}
            onChange={(e) =>
              setForm({ ...form, duration: e.target.value })
            }
          />

          <TextField
            label="Calories Burned"
            type="number"
            value={form.caloriesBurned}
            onChange={(e) =>
              setForm({ ...form, caloriesBurned: e.target.value })
            }
          />



          <Button
            variant="contained"
            color="warning"
            onClick={handleSubmit}
          >
            Save Activity
          </Button>

        </div>

        

        <h3 className="text-2xl text-orange-500 mb-4 font-bold ">
          Your Activities
        </h3>

        {activities.length === 0 && (
          <p className="text-gray-400">No activities yet</p>
        )}

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">

          {activities.map((act) => (
            <ActivityCard
              key={act.id}
              activity={act}
              onDelete={handleDelete}
            />
          ))}

        </div>

      </div>
    </>
  );
}