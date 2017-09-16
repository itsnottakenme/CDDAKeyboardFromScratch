package cdda.com.cddakeyboardfromscratch;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;

/**
 * Created by ian on 9/10/2017.
 */



public class ImeService extends InputMethodService
{
    View vKeyboard;

    private InputMethodManager mInputMethodManager;

    @Override
    public void onCreate() {
        //todo: remove debug code
        //android.os.Debug.waitForDebugger();




        super.onCreate();
        mInputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//        mWordSeparators = getResources().getString(R.string.word_separators);
//        final TextServicesManager tsm = (TextServicesManager) getSystemService(
//                Context.TEXT_SERVICES_MANAGER_SERVICE);
//        mScs = tsm.newSpellCheckerSession(null, null, this, true);
        return;
    }

    /**
     * This is the point where you can do all of your UI initialization.  It
     * is called after creation and any configuration change.
     */
    @Override
    public void onInitializeInterface() {
    }

    /**
     * Called by the framework when your view for creating keyboardview_base needs to
     * be generated.  This will be called the first time your keyboardview_base method
     * is displayed, and every time it needs to be re-created such as due to
     * a configuration change.
     */
    @Override
    public View onCreateInputView() {
        vKeyboard= getLayoutInflater().inflate(R.layout.keyboard, null);


        return vKeyboard;
    }


    /**
     * Called by the framework when your view for showing candidates needs to
     * be generated, like {@link #onCreateInputView}.
     */
    @Override
    public View onCreateCandidatesView()
    {
        return null;
    }

    /**
     * This is the main point where we do our initialization of the keyboardview_base method
     * to begin operating on an application.  At this point we have been
     * bound to the client, and are now receiving all of the detailed information
     * about the target of our edits.
     */
    @Override
    public void onStartInput(EditorInfo attribute, boolean restarting)
    {
        super.onStartInput(attribute, restarting);
    }

    /**
     * This is called when the user is done editing a field.  We can use
     * this to reset our state.
     */
    @Override
    public void onFinishInput()
    {
        super.onFinishInput();
        if (vKeyboard!=null)
        {
            //todo: close/reset keyboard
            //vKeyboard.closing();
        }

    }

    @Override
    public void onStartInputView(EditorInfo attribute, boolean restarting)
    {
        super.onStartInputView(attribute, restarting);
    }










}
