import Navbar from "../components/Navbar";

export default function Home() {
  return (
    <>
      <Navbar />
      <div className="min-h-screen bg-[#060606] text-white flex flex-col items-center pt-48 ">
        <h1 className=" typing-text ">
          Track Your Activity. Improve Your Fitness.
        </h1>
        <p className="mt-4 text-gray-400 text-lg">

          Personalized Fitness Recommendations for Your Activity.

        </p>
      </div>
    </>
  );
}
