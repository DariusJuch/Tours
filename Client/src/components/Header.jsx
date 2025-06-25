 import { Link, NavLink, useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

const Header =() => {
  const navigate = useNavigate();
    const {isLoggedIn, user, logout} = useContext(AuthContext)


  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <header className="bg-white shadow-md p-4 sticky top-0 z-50">
      <div className="container mx-auto flex justify-between items-center">
        <Link to="/" className="text-xl font-bold text-blue-600">
          TourApp
        </Link>

        <nav className="flex gap-6 items-center">
          <NavLink
            to="/"
            className={({ isActive }) =>
              isActive
                ? 'text-blue-600 font-semibold'
                : 'text-gray-700 hover:text-blue-600'
            }
          >
            Home
          </NavLink>

          <NavLink
            to="/excursions"
            className={({ isActive }) =>
              isActive
                ? 'text-blue-600 font-semibold'
                : 'text-gray-700 hover:text-blue-600'
            }
          >
            Excursions
          </NavLink>

          {!isLoggedIn ? (
            <>
              <NavLink
                to="/login"
                className="text-gray-700 hover:text-blue-600"
              >
                Login
              </NavLink>
            </>
          ) : (
            <button
              onClick={handleLogout}
              className="text-red-600 font-medium hover:underline"
            >
              Logout
            </button>
          )}
        </nav>
      </div>
    </header>
  );
}
export default Header