import { React,useEffect, useState} from 'react';
import { useParams } from 'react-router';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { YearSelector } from '../components/YearSelector';
import './MatchPage.scss'
export const MatchPage = () => {
    const[matches,setMatches] = useState([]);
    const {teamName,year} = useParams();

    // const teamName = "Los Angeles Valiant";
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch (`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                setMatches(data);
                console.log(data);
            };
            fetchMatches();
        },[teamName,year]

    );

    return (
        <div className = "MatchPage">
            <div className = "year-selector">
                <h4>Select a year</h4>
                <YearSelector teamName = {teamName}/>
            </div>
        <div>    
        <h1 className = "page-heading"> For the year {year} {teamName} data</h1>{
                    matches.map(match => <MatchDetailCard   key = {match.id}teamName = {teamName} match = {match}/>)}
        </div>

        </div>

    )
    //http://localhost:8080/team/Los%20Angeles%20Valiant/matches?year=2020
}