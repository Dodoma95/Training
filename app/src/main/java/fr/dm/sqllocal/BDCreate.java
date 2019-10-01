package fr.dm.sqllocal;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BDCreate extends Activity implements View.OnClickListener {

    private Button buttonBDCreate;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bdcreate);

        buttonBDCreate = (Button) findViewById(R.id.buttonBDCreate);
        buttonBDCreate.setOnClickListener(this);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    }

    @Override
    public void onClick(View v) {

        StringBuilder lsbMessage = new StringBuilder();
        GestionnaireOuvertureSQLite gos;
        SQLiteDatabase bd;

        try {
            // Connexion
            // --- GestionnaireOuvertureSQLite(Contexte, Fabrique de curseur);
            gos = new GestionnaireOuvertureSQLite(this, null);
            bd = gos.getWritableDatabase();

            lsbMessage.append("Connexion réussie");
            lsbMessage.append("\n");

            // Creation de la table
            // cf GOS

            // Insertion dans la table
            // cf GOS + ce qui suit
            ContentValues hmValeurs = new ContentValues();
            hmValeurs.put("nom_Pays", "Italie");
            long llNum = bd.insert("pays", null, hmValeurs);

            // Visualisation de la table
            // --- Sans WHERE : une projection
            String[] cols = {"id_pays", "nom_pays"};
            Cursor curseur = bd.query("pays", cols, null, null, null, null, null);
            while (curseur.moveToNext()) {
                lsbMessage.append(curseur.getInt(0));
                lsbMessage.append("-");
                lsbMessage.append(curseur.getString(1));
                lsbMessage.append("\n");
            }
            curseur.close();

            // Deconnexion
            gos.close();
            lsbMessage.append("Vous êtes déconnecté(e) !");
            lsbMessage.append("\n");
            bd = null;

        } catch (Exception e) {
            lsbMessage.append("Aïe, aïe, aïe ! ");
            lsbMessage.append(e.getMessage());
            lsbMessage.append("\n");
        }

        textViewMessage.setText(lsbMessage.toString());
    } /// onClick
} //// classe
