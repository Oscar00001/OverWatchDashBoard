import { React,useEffect, useState} from 'react';
import { useParams,Link } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { TeamTile } from '../components/TeamTile';
import './HomePage.scss';
export const HomePage = () => {
    const [teams,setTeams] = useState([]);
    useEffect(
        () => {
            const fetchAllTeams = async () => {
                const response = await fetch (`http://localhost:8080/team`);
                const data = await response.json();
                setTeams(data);
                console.log(data);
            };
            fetchAllTeams();
        },[]

    );

    return (
        <div className = "HomePage">
        <div className= "header-section">
            <h1 className = "app-name">Overwatch Dashboard</h1>
        </div>
        <div className = "team-grid">
            {teams.map(team => <TeamTile key = {team.id} teamName = {team.teamName} /> )}


        </div>
        </div>
        //
        //http://localhost:3000/
        //http://localhost:8080/team/Los%20Angeles%20Valiant
        //http://localhost:3000/teams/San%20Francisco%20Shock
    )
}