
import { Route, Routes } from 'react-router';

import "./App.css";
import MainLayout from './components/MainLayout';
import Home from './page/Home';
import Login from './page/Login'

function App() {
  return (
    <> 
      <Routes>
        <Route path="/" element={<MainLayout/>}>
          <Route index element={<Home />}/>
          <Route 
            path="/login"
            element={
              <Login />
            }
          />
        </Route>
      </Routes>
    </>
  );
}

export default App;
