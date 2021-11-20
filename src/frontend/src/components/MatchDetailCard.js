import { React} from 'react';
import { Link } from 'react-router-dom';
import "./MatchDetailCard.scss"
export const MatchDetailCard = ({teamName,match}) => {
    if (!match) return null;
    const otherTeam = match.attacker === teamName? match.defender : match.attacker;
    const otherTeamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.matchWinner;
    return (
        // <div className = "MatchDetailCard">
        < div className ={isMatchWon? 'MatchDetailCard won-card' : 'MatchDetailCard lost-card'}>
            <span className = "vs">vs</span>
            {/* <h3>Latest Matches </h3> */}
            <div>            
            <h1 className ="testing"> vs <Link to = {otherTeamRoute} > {otherTeam}</Link></h1>
            <h2 className = "match-date">{match.roundStartTime}</h2>
            <h3 className = "match-map"> at {match.mapName} here</h3>
            <h3 className = "match-result"> {match.mapWinner} won by {match.winningTeamFinalMapScore} map score</h3>
            <h3 className = "match-result">match winner was {match.matchWinner}</h3>
            </div>
            <div className = "additional-detail">
            {/* <h4>{match.attacker} vs {match.defender}</h4> */}
            <h3>Attacker</h3>
            <p>{match.attacker}</p>
            <h3>Defender</h3>
            <p>{match.defender}</p>
            </div>

        </div>
    
    )
}