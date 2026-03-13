import {
  Card,
  CardContent,
  Typography,
  Chip,
  IconButton,
  Stack
} from "@mui/material";

import DeleteIcon from "@mui/icons-material/Delete";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import LocalFireDepartmentIcon from "@mui/icons-material/LocalFireDepartment";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";

export default function ActivityCard({ activity, onDelete }) {

  if (!activity) return null;

  return (
    <Card
      sx={{
        mb: 3,
        background: "linear-gradient(145deg, #1f1f1f, #111)",
        color: "white",
        borderRadius: "16px",
        boxShadow: "0 6px 20px rgba(0,0,0,0.6)",
        position: "relative",
        transition: "0.3s",
        "&:hover": {
          transform: "scale(1.03)"
        }
      }}
    >

      
      <IconButton
        onClick={() => onDelete(activity.id)}
        sx={{
          position: "absolute",
          top: 10,
          right: 10,
          color: "#ff4d4d"
        }}
      >
        <DeleteIcon />
      </IconButton>

      <CardContent>

        <Typography
          variant="h6"
          sx={{ color: "#ff6a00", fontWeight: "bold", mb: 2 }}
        >
         
          {activity.type}
        </Typography>


        <Stack direction="row" spacing={2} sx={{ mb: 2 }}>
          <Chip
            icon={<AccessTimeIcon />}
            label={`${activity.duration} min`}
            color="primary"
          />

          <Chip
            icon={<LocalFireDepartmentIcon />}
            label={`${activity.caloriesBurned} kcal`}
            color="error"
          />
        </Stack>

        
        <Typography variant="body2" color="gray">
  Start Time: {activity.startTime
    ? new Date(activity.startTime).toLocaleString("en-IN", {
        dateStyle: "medium",
        timeStyle: "short",
      })
    : new Date(Date.now() + Math.floor(Math.random() * 3600000)) // random up to 1 hour
        .toLocaleString("en-IN", { dateStyle: "medium", timeStyle: "short" })}
</Typography>

        
        {activity.additionalMetrics &&
          Object.keys(activity.additionalMetrics).length > 0 && (
            <Stack direction="row" spacing={1} flexWrap="wrap">
              {Object.entries(activity.additionalMetrics).map(
                ([key, value]) => (
                  <Chip
                    key={key}
                    label={`${key}: ${value}`}
                    size="small"
                    sx={{
                      backgroundColor: "#333",
                      color: "white"
                    }}
                  />
                )
              )}
            </Stack>
          )}

      </CardContent>
    </Card>
  );
}