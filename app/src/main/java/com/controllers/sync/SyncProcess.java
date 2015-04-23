package com.controllers.sync;

import android.content.Context;

import com.controllers.sync.interfaces.SyncInterface;
import com.models.User;
import com.models.mappers.CheckListMapper;
import com.models.mappers.FileMapper;
import com.models.mappers.LinksMapper;
import com.models.mappers.NoteMapper;
import com.models.mappers.TagMapper;
import com.models.mappers.UserMapper;

/**
 * @(#)JceSecurity.java 1.50 04/04/14
 *
 * Esta clase se encarga de la sincronizacion asincrona
 *
 * @author Carlos Herrera
 *
 * @version 1.50, 014/04/15
 * @since 1.4
 */
public class SyncProcess implements SyncInterface{

    public SyncProcess (Context context){
        checkListMapper = new CheckListMapper(context);
        fileMapper = new FileMapper(context);
        linksMapper = new LinksMapper(context);
        noteMapper = new NoteMapper(context);
        tagMapper = new TagMapper(context);
        userMapper = new UserMapper(context);

        notesHandler = new SyncNotesHandler(context, this);
    }

    public void startLoginSync(User user){
        //paso 1 obtener las notas
        noteMapper.dropNotes();
        notesHandler.getNotesFromuser(user.getToken());
    }

    @Override
    public void onResponse(Object response) {

    }

    @Override
    public void onError(int StatusCode, String error) {

    }

    private CheckListMapper checkListMapper;
    private FileMapper fileMapper;
    private LinksMapper linksMapper;
    private NoteMapper noteMapper;
    private TagMapper tagMapper;
    private UserMapper userMapper;

    private SyncNotesHandler notesHandler;
}
