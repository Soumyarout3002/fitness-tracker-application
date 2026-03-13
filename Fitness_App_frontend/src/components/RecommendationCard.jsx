import { Card, CardContent, Typography, Chip } from "@mui/material";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import LightbulbIcon from "@mui/icons-material/Lightbulb";
import WarningAmberIcon from "@mui/icons-material/WarningAmber";

export default function RecommendationCard({ reco }) {
  return (
    <Card
      sx={{
        background: "linear-gradient(145deg, #1f1f1f, #111)",
        color: "white",
        borderRadius: "16px",
        boxShadow: "0 6px 20px rgba(0,0,0,0.6)",
        transition: "0.3s",
        "&:hover": {
          transform: "scale(1.03)"
        }
      }}
    >
      <CardContent>
        <Typography
          variant="h6"
          sx={{ color: "#ff6a00", fontWeight: "bold", mb: 2 }}
        >
          {reco.type}
        </Typography>

        {reco.improvements?.length > 0 && (
          <>
            <Chip
              icon={<FitnessCenterIcon />}
              label="Improvements"
              color="success"
              sx={{ mb: 1 }}
            />

            <ul style={{ marginLeft: "20px", marginBottom: "12px" }}>
              {reco.improvements.map((imp, i) => (
                <li key={i}>{imp}</li>
              ))}
            </ul>
          </>
        )}

        {reco.suggestions?.length > 0 && (
          <>
            <Chip
              icon={<LightbulbIcon />}
              label="Suggestions"
              color="primary"
              sx={{ mb: 1 }}
            />

            <ul style={{ marginLeft: "20px", marginBottom: "12px" }}>
              {reco.suggestions.map((s, i) => (
                <li key={i}>{s}</li>
              ))}
            </ul>
          </>
        )}

    
        {reco.safety?.length > 0 && (
          <>
            <Chip
              icon={<WarningAmberIcon />}
              label="Safety Tips"
              color="warning"
              sx={{ mb: 1 }}
            />

            <ul style={{ marginLeft: "20px" }}>
              {reco.safety.map((safe, i) => (
                <li key={i}>{safe}</li>
              ))}
            </ul>
          </>
        )}

      </CardContent>
    </Card>
  );
}