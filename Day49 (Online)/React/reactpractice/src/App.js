import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Greetings from './Components/Greetings';
import Counter from './Components/Counter';
import FetchUsersData from './Components/FetchUsersData';
import ConditionalMessage from './Components/ConditionalRendering';
import Calculator from './Components/Calculator';

function App() {

  return (
    <Router>
      <div className='App'>
        <nav style={{ marginBottom: '20px' }}>
          <Link to="/greeting" style={{ margin: '0 10px' }}>Greetings</Link>
          <Link to="/counter" style={{ margin: '0 10px' }}>Counter</Link>
          <Link to="/users" style={{ margin: '0 10px' }}>Fetch Users</Link>
          <Link to="/conditional-rendering" style={{ margin: "0 10px" }}>Conditional Rendering Examples</Link>
          <Link to="/calculator" style={{ margin: "0 10px" }}>Calculator</Link>
        </nav>

        <Routes>
          <Route path="/greeting" element={<Greetings name="KMIT" />} />
          <Route path="/counter" element={<Counter />} />
          <Route path="/users" element={<FetchUsersData />} />
          <Route path="/conditional-rendering" element={<ConditionalMessage />} />
          <Route path="/calculator" element={<Calculator />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;