import javafx.css.Match;

import java.util.List;

public class Stat {
    private int m_iMatchesPlayed;
    private int m_iNumGamesPlayed;
    private int m_iNumWins;
    private int m_iNumGameWins;
    private int m_iNumLosses;
    private int m_iNumGameLosses;
    private int m_iNumDraws;
    private float m_fWinPerc;
    private float m_fLossPerc;
    private float m_fDrawPerc;
    private float m_fGameWinPerc;
    private float m_fGameLossPerc;

    public Stat()
    {
        m_iMatchesPlayed = 0;
        m_iNumGamesPlayed = 0;
        m_iNumWins = 0;
        m_iNumGameWins = 0;
        m_iNumLosses = 0;
        m_iNumGameLosses = 0;
        m_iNumDraws = 0;

        m_fWinPerc = 0;
        m_fLossPerc = 0;
        m_fDrawPerc = 0;
        m_fGameWinPerc = 0;
        m_fGameWinPerc = 0;
    }

    public Stat(List<MatchData> list)
    {
        this();
        generateStats(list);
    }
    public void generateStats(List<MatchData> list)
    {
        for(MatchData match : list)
        {
            addMatchHelper(match);
        }
        updatePerc();
    }

    private void updatePerc()
    {
        m_fWinPerc = 100 * (float)m_iNumWins / m_iMatchesPlayed;
        m_fLossPerc = 100 * (float)m_iNumLosses / m_iMatchesPlayed;
        m_fDrawPerc = 100 * (float)m_iNumDraws / m_iMatchesPlayed;
        m_fGameWinPerc = 100 * (float)m_iNumGameWins / m_iNumGamesPlayed;
        m_fGameLossPerc = 100 * (float)m_iNumGameLosses / m_iNumGamesPlayed;
    }

    private void addMatchHelper(MatchData match)
    {
        String matchRes = match.getResult();

        if ( matchRes == null )
            return;

        if ( matchRes.startsWith("Win") )
            m_iNumWins++;
        if ( matchRes.startsWith("Loss") )
            m_iNumLosses++;
        if ( matchRes.startsWith("Draw") )
            m_iNumDraws++;

        m_iMatchesPlayed++;

        int[] iResult = MatchData.parseResult( matchRes );
        if ( iResult.length != 3 )
            return;
        m_iNumGameWins += iResult[0];
        m_iNumGameLosses += iResult[1];
        m_iNumGamesPlayed += iResult[0] + iResult[1];

    }

    public void addMatch(MatchData match)
    {
        addMatchHelper(match);
        updatePerc();
    }

    public int getMatchesPlayed() {
        return m_iMatchesPlayed;
    }

    public int getNumGamesPlayed() {
        return m_iNumGamesPlayed;
    }

    public int getNumWins() {
        return m_iNumWins;
    }

    public int getNumGameWins() {
        return m_iNumGameWins;
    }

    public int getNumLosses() {
        return m_iNumLosses;
    }

    public int getNumGameLosses() {
        return m_iNumGameLosses;
    }

    public int getNumDraws() {
        return m_iNumDraws;
    }

    public float getWinPerc() {
        return m_fWinPerc;
    }

    public float getLossPerc() {
        return m_fLossPerc;
    }

    public float getDrawPerc() {
        return m_fDrawPerc;
    }

    public float getGameWinPerc() {
        return m_fGameWinPerc;
    }

    public float getGameLossPerc() {
        return m_fGameLossPerc;
    }
}