import { React,useEffect, useState} from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const TeamPage = () => {
    const [team,setTeam] = useState({matches:[]});
    const {teamName} = useParams();
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch (`http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                setTeam(data);
                console.log(data);
            };
            fetchMatches();
        },[teamName]

    );

    if (!team || !team.teamName){
        return <h1> Team does not exist!!!!!</h1>
    }
    return (
        <div className = "TeamPage">
        <MatchDetailCard/>
        <MatchSmallCard/>
        <h1> {team.teamName}</h1>
        <MatchDetailCard teamName = {team.teamName} match = {team.matches[0]}/>
        {team.matches.slice(1).map(match => <MatchSmallCard match = {match}/>)}
        </div>
        //
        //http://localhost:3000/
        //http://localhost:8080/team/Los%20Angeles%20Valiant
    )
}