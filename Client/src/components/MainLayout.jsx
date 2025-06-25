import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";


const MainLayout = () => {

  return (
    <>
      <div className="relative min-h-screen">
        <div className="relative z-10">
          <Header/>
          <div className="flex-1">
            <Outlet />
          </div>
        
        </div>
      </div>
      <Footer />
    </>
  );
};

export default MainLayout;