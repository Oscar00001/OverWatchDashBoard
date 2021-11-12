import { React} from 'react';

export const MatchDetailCard = ({teamName,match}) => {
    if (!match) return null;
    const otherTeam = match.attacker === teamName? match.defender : match.attacker;
    return (
        <div className = "MatchDetailCard">
            <h3>Latest Matches </h3>
            <h1> vs {otherTeam} </h1>
            <h2>{match.roundStartTime}</h2>
            <h3> at {match.mapName} here</h3>
            <h3> {match.mapWinner} won by {match.winningTeamFinalMapScore} map score and match winner was {match.matchWinner}</h3>
            {/* <h4>{match.attacker} vs {match.defender}</h4> */}
        </div>
    
    )
}