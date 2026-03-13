import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home.jsx";
import Activity from "../pages/Activity.jsx";
import Recommendation from "../pages/Recommendation.jsx";
import Login from "../pages/Login.jsx";
import Register from "../pages/Register.jsx";
import NotFound from "../pages/NotFoundPage.jsx";
import ProtectedRoute from "../components/ProtectedRouted.jsx";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />

      <Route
        path="/"
        element={
          <ProtectedRoute>
            <Home />
          </ProtectedRoute>
        }
      />
      <Route
        path="/activity"
        element={
          <ProtectedRoute>
            <Activity />
          </ProtectedRoute>
        }
      />
      <Route
        path="/recommendation"
        element={
          <ProtectedRoute>
            <Recommendation />
          </ProtectedRoute>
        }
      />

      <Route path="*" element={<NotFound />} />
    </Routes>
  );
}


// import { Routes, Route } from "react-router-dom";
// import Home from "../pages/Home.jsx";
// import Activity from "../pages/Activity.jsx";
// import Recommendation from "../pages/Recommendation.jsx";
// import Login from "../pages/Login.jsx";
// import Register from "../pages/Register.jsx";
// import NotFound from "../pages/NotFoundPage.jsx";
// import ProtectedRoute from "../components/ProtectedRouted.jsx"; // fixed filename

// export default function AppRoutes() {
//   return (
//     <Routes>
//       <Route path="/login" element={<Login />} />
//       <Route path="/register" element={<Register />} />

//       <Route
//         path="/"
//         element={
//           <ProtectedRoute>
//             <Home />
//           </ProtectedRoute>
//         }
//       />
//       <Route
//         path="/activity"
//         element={
//           <ProtectedRoute>
//             <Activity />
//           </ProtectedRoute>
//         }
//       />
//       <Route
//         path="/recommendation"
//         element={
//           <ProtectedRoute>
//             <Recommendation />
//           </ProtectedRoute>
//         }
//       />

//       <Route path="*" element={<NotFound />} />
//     </Routes>
//   );
// }

