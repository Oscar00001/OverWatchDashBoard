import { React} from 'react';
import { Link } from 'react-router-dom';
import './MatchSmallCard.scss';
export const MatchSmallCard = ({match,teamName}) => {
    if (!match) return null;
    const otherTeam = match.attacker === teamName? match.defender : match.attacker;
    const otherTeamRoute = `/teams/${otherTeam}`
    const isMatchWon = teamName === match.matchWinner;
    return (
        < div className ={isMatchWon? 'MatchSmallCard won-card' : 'MatchSmallCard lost-card'}>
            {/* <p> Match Small Card</p> */}
            <span classNae = "vs"> vs </span>
            <h1><Link to = {otherTeamRoute}>{otherTeam} </Link></h1>
            {/* <p>{match.attacker} vs {match.defender}</p> */}
            <p className = "match-result"> {match.mapWinner} won by {match.winningTeamFinalMapScore} map score and match winner was {match.matchWinner} The map is {match.mapName}</p>

        </div>
    
    )
}