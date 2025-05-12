import './App.css';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Greetings from './Components/Greetings';
import Counter from './Components/Counter';
import FetchUsersData from './Components/FetchUsersData';

function App() {
  return (
    <Router>
      <div className='App'>
        <nav style={{ marginBottom: '20px' }}>
          <Link to="/" style={{ margin: '0 10px' }}>Greetings</Link>
          <Link to="/counter" style={{ margin: '0 10px' }}>Counter</Link>
          <Link to="/users" style={{ margin: '0 10px' }}>Fetch Users</Link>
        </nav>
        <Routes>
          <Route path="/" element={<Greetings />} />
          <Route path="/counter" element={<Counter />} />
          <Route path="/users" element={<FetchUsersData />} />
        </Routes>
      </div>
    </Router >
  );
}

export default App;
