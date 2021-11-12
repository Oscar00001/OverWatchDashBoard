import { React} from 'react';
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({match,teamName}) => {
    if (!match) return null;
    const otherTeam = match.attacker === teamName? match.defender : match.attacker;
    const otherTeamRoute = `/teams/${otherTeam}`
    return (
        <div className = "MatchSmallCard">
            {/* <p> Match Small Card</p> */}
            <h3> vs 
                <Link to = {otherTeamRoute}>{otherTeam} </Link></h3>
            {/* <p>{match.attacker} vs {match.defender}</p> */}
            <p> {match.mapWinner} won by {match.winningTeamFinalMapScore} map score and match winner was {match.matchWinner}</p>

        </div>
    
    )
}