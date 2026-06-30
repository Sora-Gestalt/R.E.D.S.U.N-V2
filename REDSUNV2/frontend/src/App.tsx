import { useState } from 'react'
import {Routes,Route,BrowserRouter} from 'react-router-dom'
import './App.css'
import Home from './pages/HomePage';
import Blogs from './pages/BlogsPage';
import Expertise from './pages/ExpertisePage';

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home></Home>}></Route>
        <Route path="/Blogs" element={<Blogs></Blogs>}></Route>
        <Route path="/Expertise" element={<Expertise></Expertise>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App
