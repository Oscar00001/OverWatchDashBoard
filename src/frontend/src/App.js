import './App.scss';
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';
import { MatchDetailCard } from './components/MatchDetailCard';
import { HomePage } from './pages/HomePage';
function App() {
  return (
    <div className ="App">
      <Router>
        <Switch>
        <Route path = "/teams/:teamName/matches/:year">
        <MatchPage />
      </Route>
      <Route path = "/teams/:teamName">
        <TeamPage/>
      </Route>
      <Route path="/">
        <HomePage />
      </Route>
      </Switch>
      </Router>
    </div>
  );
}

export default App;

//http://localhost:3000/teams/Los%20Angeles%20Valiant/matches/2020
//http://localhost:8080/team/Los%20Angeles%20Valiant/matches?year=2020
//http://localhost:3000/teams/San%20Francisco%20Shock
//http://localhost:8080/team/Los%20Angeles%20Valiant/matches?year=2020
//