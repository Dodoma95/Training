package fr.dm.sqllocal;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.Cursor;
import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

/*
 * DAO
 * Methodes : constructeur, insert, delete, selectOne, selectAll
 */
public class VilleDAO {

    private static final String TABLE_NAME = "villes";
    private SQLiteDatabase ibd;

    // --- CONSTRUCTEUR
    public VilleDAO(SQLiteDatabase bd) {
        this.ibd = bd;
    } // / VilleDAO()



    /**
     *
     * @param ville
     * @return
     */
    public boolean insert(Ville ville) {

        boolean lbOK = false;

        // --- Un enregistrement sous forme de HashMap
        ContentValues hmColonnesValeurs = new ContentValues();
        hmColonnesValeurs.put("cp", ville.getCp());
        hmColonnesValeurs.put("nom_ville", ville.getNomVille());
        hmColonnesValeurs.put("id_pays", ville.getIdPays());

        // --- Insertion
        try {
            // public long insert (String table, String nullColumnHack, ContentValues values)
            this.ibd.insert(TABLE_NAME, null, hmColonnesValeurs);
            lbOK = true;
        } catch (SQLiteException e) {
        }

        return lbOK;

    } // / insert()



    /**
     *
     * @param Ville
     * @return
     */

    public boolean delete(Object Ville) {

        boolean lbOK = false;
        Object[] tValeurs = new String[1];
        tValeurs[0] = Ville;

        try {
            // public int delete (String table, String whereClause, String[] whereArgs)
            this.ibd.delete(TABLE_NAME, "cp=?", (String[]) tValeurs);
            lbOK = true;
        } catch (SQLiteException e) {
        }

        return lbOK;

    } // / delete()



    /**
     * selectAll : renvoie tous les enregistrements
     *
     * @return une String
     * (devrait renvoyer une List d'objets,
     *  une List de Ville en l'occurrence)
     */

    public List<Ville> selectAll() {

        //Ville[] tabVilles = new Ville[0];
        List<Ville> listVilles = new ArrayList<>();

        //StringBuilder lsbResultat = new StringBuilder();

        // --- Tous les enregistrements
        try {
            String[] cols = { "cp", "nom_ville" , "id_pays"};

            Cursor curseur = this.ibd.query(TABLE_NAME, cols, null, null, null, null, null);


            //for (int i = 0; i < tabVilles.length; i++ ) {
              //  tabVilles =
            //}
            // Cursor curseur =
            // this.ibd.rawQuery("SELECT cp, nom_ville FROM villes", null);
           while (curseur.moveToNext()) {
               listVilles.add(new Ville(curseur.getString(0),(curseur.getString(1)), (curseur.getString(2))));
              /* listVilles.add(" - ");
               listVilles.add(curseur.getString(1));
               listVilles.add(" - ");
               listVilles.add(curseur.getString(2));
               listVilles.add("\n");*/
            }


        } catch (SQLiteException e) {
            //listVilles.add("Erreur de lecture ");
        }

        return listVilles;

    } // / selectAll()



    /**
     * selectOne : un enregistrement
     *
     * @param asCP
     * @return : une ville
     */
    public Ville selectOne(String asCP) {

        Ville ville;

        // --- Un enregistrement
        try {
            String[] cols = { "id_ville", "cp", "nom_ville" , "id_pays"};
            String[] tValeurs = { asCP };

            // query(String table, String[] columns, String selection, String[]
            // selectionArgs, String groupBy, String having, String orderBy)
            Cursor curseur = this.ibd.query(TABLE_NAME, cols, "cp=?", tValeurs, null, null, null);

            if (curseur.moveToNext()) {
                ville = new Ville(curseur.getInt(0), asCP, curseur.getString(2), curseur.getString(3));
               // ville.getIdVille(Integer.valueOf(curseur.getString(0)));
                //ville.setCp(asCP);
                //ville.setNomVille(curseur.getString(2));
                //ville.setIdPays(curseur.getString(3));
            } else {
                ville = new Ville(0, "", "", "");
                ville.getIdVille(0);
                ville.setCp("");
                ville.setNomVille("");
                ville.setIdPays("");
            }
        } catch (SQLiteException e) {
            ville = new Ville(0, "Erreur de lecture", e.getMessage(), "");
            ville.getIdVille(0);
            ville.setCp("Erreur de lecture ");
            ville.setNomVille(e.getMessage());
            ville.setIdPays("");
        }

        return ville;

    } // / selectOne()

    public int updateOne(Ville ville) {


        ContentValues values = new ContentValues();
        values.put("cp", ville.getCp());
        values.put("nom_ville", ville.getNomVille());
        values.put("id_pays", ville.getIdPays());

        String[] tValeur = new String[1];
        tValeur[0] = ville.getCp();

        // updating row
        return ibd.update(TABLE_NAME, values, "cp = ?", tValeur);

    }

} // / class VilleDAO
