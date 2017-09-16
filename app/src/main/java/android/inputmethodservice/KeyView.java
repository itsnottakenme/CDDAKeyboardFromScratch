package android.inputmethodservice;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;

import java.util.StringTokenizer;

import static android.content.ContentValues.TAG;
import static android.inputmethodservice.Keyboard.EDGE_BOTTOM;
import static android.inputmethodservice.Keyboard.EDGE_LEFT;
import static android.inputmethodservice.Keyboard.EDGE_RIGHT;
import static android.inputmethodservice.Keyboard.EDGE_TOP;

/**
 * Created by ian on 9/13/2017.
 */

public class KeyView extends View
{


    /**
     * All the key codes (unicode or custom code) that this key could generate, zero'th
     * being the most important.
     */
    public int[] codes;

    /**
     * Label to display
     */
    public CharSequence label;

    /**
     * Icon to display instead of a label. Icon takes precedence over a label
     */
    public Drawable icon;
    /**
     * Preview version of the icon, for the preview popup
     */
    public Drawable iconPreview;
    /**
     * Width of the key, not including the gap
     */
    public int width;
    /**
     * Height of the key, not including the gap
     */
    public int height;
    /**
     * The horizontal gap before this key
     */
    public int gap;
    /**
     * Whether this key is sticky, i.e., a toggle key
     */
    public boolean sticky;
    /**
     * X coordinate of the key in the keyboard layout
     */
    public int x;
    /**
     * Y coordinate of the key in the keyboard layout
     */
    public int y;
    /**
     * The current pressed state of this key
     */
    public boolean pressed;
    /**
     * If this is a sticky key, is it on?
     */
    public boolean on;
    /**
     * Text to output when pressed. This can be multiple characters, like ".com"
     */
    public CharSequence text;
    /**
     * Popup characters
     */
    public CharSequence popupCharacters;

    /**
     * Flags that specify the anchoring to edges of the keyboard for detecting touch events
     * that are just out of the boundary of the key. This is a bit mask of
     * {@link Keyboard#EDGE_LEFT}, {@link Keyboard#EDGE_RIGHT}, {@link Keyboard#EDGE_TOP} and
     * {@link Keyboard#EDGE_BOTTOM}.
     */
    public int edgeFlags;
    /**
     * Whether this is a modifier key, such as Shift or Alt
     */
    public boolean modifier;


    /**
     * The keyboard that this key belongs to
     */
    private Keyboard keyboard;
    /**
     * If this key pops up a mini keyboard, this is the resource id for the XML layout for that
     * keyboard.
     */
    public int popupResId;
    /**
     * Whether this key repeats itself when held down
     */
    public boolean repeatable;


    private final static int[] KEY_STATE_NORMAL_ON = {
            android.R.attr.state_checkable,
            android.R.attr.state_checked
    };

    private final static int[] KEY_STATE_PRESSED_ON = {
            android.R.attr.state_pressed,
            android.R.attr.state_checkable,
            android.R.attr.state_checked
    };

    private final static int[] KEY_STATE_NORMAL_OFF = {
            android.R.attr.state_checkable
    };

    private final static int[] KEY_STATE_PRESSED_OFF = {
            android.R.attr.state_pressed,
            android.R.attr.state_checkable
    };

    private final static int[] KEY_STATE_NORMAL = {
    };

    private final static int[] KEY_STATE_PRESSED = {
            android.R.attr.state_pressed
    };



    //    public void init   (int[] codes, CharSequence label, Drawable icon, Drawable iconPreview,
//                        int width, int height, int gap, boolean sticky, int x, int y, boolean pressed,
//                        boolean on, CharSequence text, CharSequence popupCharacters, int edgeFlags,
//                        boolean modifier, Keyboard keyboard, int popupResId, boolean repeatable)
//    {
//
//    }


    public KeyView(Context context)
    {
        super(context);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public KeyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /** Use Keyboard.Key to generate Keys from XML
     * This method just populates with those values
     * @param k
     */
    public void init(Keyboard.Key k)
    {
        setCodes(k.codes);
        setLabel(k.label);
        setIcon(k.icon);
        setIconPreview(k.iconPreview);
        //todo: must use View's methods for these:
//        setWidth(k.width);
//        setHeight(k.height);


        setSticky(k.sticky);
        setX(k.x);
        setY(k.y);
        setPressed(k.pressed);
        setOn(k.on);
        setText(k.text);
        setPopupCharacters(k.popupCharacters);
        setEdgeFlags(k.edgeFlags);
        setModifier(k.modifier);
        //setKeyboard(k.k); //private varaiable so can't be done
        setPopupResId(k.popupResId);
        setRepeatable(k.repeatable);

        return;
    }
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }



























    public int[] getCodes()
    {
        return codes;
    }

    public void setCodes(int[] codes)
    {
        this.codes = codes;
    }

    public CharSequence getLabel()
    {
        return label;
    }

    public void setLabel(CharSequence label)
    {
        this.label = label;
    }

    public Drawable getIcon()
    {
        return icon;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public Drawable getIconPreview()
    {
        return iconPreview;
    }

    public void setIconPreview(Drawable iconPreview)
    {
        this.iconPreview = iconPreview;
    }

//    public int getWidth()
//    {
//        return width;
//    }
//
//    public void setWidth(int width)
//    {
//        this.width = width;
//    }
//
//    public int getHeight()
//    {
//        return height;
//    }
//
//    public void setHeight(int height)
//    {
//        this.height = height;
//    }

    public int getGap()
    {
        return gap;
    }

    public void setGap(int gap)
    {
        this.gap = gap;
    }

    public boolean isSticky()
    {
        return sticky;
    }

    public void setSticky(boolean sticky)
    {
        this.sticky = sticky;
    }

//    public int getX()
//    {
//        return x;
//    }
//
//    public void setX(int x)
//    {
//        this.x = x;
//    }
//
//    public int getY()
//    {
//        return y;
//    }
//
//    public void setY(int y)
//    {
//        this.y = y;
//    }

    public boolean isPressed()
    {
        return pressed;
    }

    public void setPressed(boolean pressed)
    {
        this.pressed = pressed;
    }

    public boolean isOn()
    {
        return on;
    }

    public void setOn(boolean on)
    {
        this.on = on;
    }

    public CharSequence getText()
    {
        return text;
    }

    public void setText(CharSequence text)
    {
        this.text = text;
    }

    public CharSequence getPopupCharacters()
    {
        return popupCharacters;
    }

    public void setPopupCharacters(CharSequence popupCharacters)
    {
        this.popupCharacters = popupCharacters;
    }

    public int getEdgeFlags()
    {
        return edgeFlags;
    }

    public void setEdgeFlags(int edgeFlags)
    {
        this.edgeFlags = edgeFlags;
    }

    public boolean isModifier()
    {
        return modifier;
    }

    public void setModifier(boolean modifier)
    {
        this.modifier = modifier;
    }


    public Keyboard getKeyboard()
    {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard)
    {
        this.keyboard = keyboard;
    }


    public int getPopupResId()
    {
        return popupResId;
    }



    public void setPopupResId(int popupResId)
    {
        this.popupResId = popupResId;
    }

    public boolean isRepeatable()
    {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable)
    {
        this.repeatable = repeatable;
    }




































//    /**
//     * Class for describing the position and characteristics of a single key in the keyboard.
//     *
//     * @attr ref android.R.styleable#Keyboard_keyWidth
//     * @attr ref android.R.styleable#Keyboard_keyHeight
//     * @attr ref android.R.styleable#Keyboard_horizontalGap
//     * @attr ref android.R.styleable#Keyboard_Key_codes
//     * @attr ref android.R.styleable#Keyboard_Key_keyIcon
//     * @attr ref android.R.styleable#Keyboard_Key_keyLabel
//     * @attr ref android.R.styleable#Keyboard_Key_iconPreview
//     * @attr ref android.R.styleable#Keyboard_Key_isSticky
//     * @attr ref android.R.styleable#Keyboard_Key_isRepeatable
//     * @attr ref android.R.styleable#Keyboard_Key_isModifier
//     * @attr ref android.R.styleable#Keyboard_Key_popupKeyboard
//     * @attr ref android.R.styleable#Keyboard_Key_popupCharacters
//     * @attr ref android.R.styleable#Keyboard_Key_keyOutputText
//     * @attr ref android.R.styleable#Keyboard_Key_keyEdgeFlags
//     */
//    public static class Key
//    {
//        /**
//         * All the key codes (unicode or custom code) that this key could generate, zero'th
//         * being the most important.
//         */
//        public int[] codes;
//
//        /**
//         * Label to display
//         */
//        public CharSequence label;
//
//        /**
//         * Icon to display instead of a label. Icon takes precedence over a label
//         */
//        public Drawable icon;
//        /**
//         * Preview version of the icon, for the preview popup
//         */
//        public Drawable iconPreview;
//        /**
//         * Width of the key, not including the gap
//         */
//        public int width;
//        /**
//         * Height of the key, not including the gap
//         */
//        public int height;
//        /**
//         * The horizontal gap before this key
//         */
//        public int gap;
//        /**
//         * Whether this key is sticky, i.e., a toggle key
//         */
//        public boolean sticky;
//        /**
//         * X coordinate of the key in the keyboard layout
//         */
//        public int x;
//        /**
//         * Y coordinate of the key in the keyboard layout
//         */
//        public int y;
//        /**
//         * The current pressed state of this key
//         */
//        public boolean pressed;
//        /**
//         * If this is a sticky key, is it on?
//         */
//        public boolean on;
//        /**
//         * Text to output when pressed. This can be multiple characters, like ".com"
//         */
//        public CharSequence text;
//        /**
//         * Popup characters
//         */
//        public CharSequence popupCharacters;
//
//        /**
//         * Flags that specify the anchoring to edges of the keyboard for detecting touch events
//         * that are just out of the boundary of the key. This is a bit mask of
//         * {@link Keyboard#EDGE_LEFT}, {@link Keyboard#EDGE_RIGHT}, {@link Keyboard#EDGE_TOP} and
//         * {@link Keyboard#EDGE_BOTTOM}.
//         */
//        public int edgeFlags;
//        /**
//         * Whether this is a modifier key, such as Shift or Alt
//         */
//        public boolean modifier;
//        /**
//         * The keyboard that this key belongs to
//         */
//        private Keyboard keyboard;
//        /**
//         * If this key pops up a mini keyboard, this is the resource id for the XML layout for that
//         * keyboard.
//         */
//        public int popupResId;
//        /**
//         * Whether this key repeats itself when held down
//         */
//        public boolean repeatable;
//
//
//        private final static int[] KEY_STATE_NORMAL_ON = {
//                android.R.attr.state_checkable,
//                android.R.attr.state_checked
//        };
//
//        private final static int[] KEY_STATE_PRESSED_ON = {
//                android.R.attr.state_pressed,
//                android.R.attr.state_checkable,
//                android.R.attr.state_checked
//        };
//
//        private final static int[] KEY_STATE_NORMAL_OFF = {
//                android.R.attr.state_checkable
//        };
//
//        private final static int[] KEY_STATE_PRESSED_OFF = {
//                android.R.attr.state_pressed,
//                android.R.attr.state_checkable
//        };
//
//        private final static int[] KEY_STATE_NORMAL = {
//        };
//
//        private final static int[] KEY_STATE_PRESSED = {
//                android.R.attr.state_pressed
//        };
//
//        /**
//         * Create an empty key with no attributes.
//         */
//        public Key(Keyboard.Row parent)
//        {
//            keyboard = parent.parent;
//            height = parent.defaultHeight;
//            width = parent.defaultWidth;
//            gap = parent.defaultHorizontalGap;
//            edgeFlags = parent.rowEdgeFlags;
//        }
//
//        /**
//         * Create a key with the given top-left coordinate and extract its attributes from
//         * the XML parser.
//         *
//         * @param res    resources associated with the caller's context
//         * @param parent the row that this key belongs to. The row must already be attached to
//         *               a {@link Keyboard}.
//         * @param x      the x coordinate of the top-left
//         * @param y      the y coordinate of the top-left
//         * @param parser the XML parser containing the attributes for this key
//         */
//        public Key(Resources res, Keyboard.Row parent, int x, int y, XmlResourceParser parser)
//        {
//            this(parent);
//
//            this.x = x;
//            this.y = y;
//
//            TypedArray a = res.obtainAttributes(Xml.asAttributeSet(parser),
//                    com.android.internal.R.styleable.Keyboard);
//
//            width = getDimensionOrFraction(a,
//                    com.android.internal.R.styleable.Keyboard_keyWidth,
//                    keyboard.mDisplayWidth, parent.defaultWidth);
//            height = getDimensionOrFraction(a,
//                    com.android.internal.R.styleable.Keyboard_keyHeight,
//                    keyboard.mDisplayHeight, parent.defaultHeight);
//            gap = getDimensionOrFraction(a,
//                    com.android.internal.R.styleable.Keyboard_horizontalGap,
//                    keyboard.mDisplayWidth, parent.defaultHorizontalGap);
//            a.recycle();
//            a = res.obtainAttributes(Xml.asAttributeSet(parser),
//                    com.android.internal.R.styleable.Keyboard_Key);
//            this.x += gap;
//            TypedValue codesValue = new TypedValue();
//            a.getValue(com.android.internal.R.styleable.Keyboard_Key_codes,
//                    codesValue);
//            if (codesValue.type == TypedValue.TYPE_INT_DEC
//                    || codesValue.type == TypedValue.TYPE_INT_HEX)
//            {
//                codes = new int[]{codesValue.data};
//            } else if (codesValue.type == TypedValue.TYPE_STRING)
//            {
//                codes = parseCSV(codesValue.string.toString());
//            }
//
//            iconPreview = a.getDrawable(com.android.internal.R.styleable.Keyboard_Key_iconPreview);
//            if (iconPreview != null)
//            {
//                iconPreview.setBounds(0, 0, iconPreview.getIntrinsicWidth(),
//                        iconPreview.getIntrinsicHeight());
//            }
//            popupCharacters = a.getText(
//                    com.android.internal.R.styleable.Keyboard_Key_popupCharacters);
//            popupResId = a.getResourceId(
//                    com.android.internal.R.styleable.Keyboard_Key_popupKeyboard, 0);
//            repeatable = a.getBoolean(
//                    com.android.internal.R.styleable.Keyboard_Key_isRepeatable, false);
//            modifier = a.getBoolean(
//                    com.android.internal.R.styleable.Keyboard_Key_isModifier, false);
//            sticky = a.getBoolean(
//                    com.android.internal.R.styleable.Keyboard_Key_isSticky, false);
//            edgeFlags = a.getInt(com.android.internal.R.styleable.Keyboard_Key_keyEdgeFlags, 0);
//            edgeFlags |= parent.rowEdgeFlags;
//
//            icon = a.getDrawable(
//                    com.android.internal.R.styleable.Keyboard_Key_keyIcon);
//            if (icon != null)
//            {
//                icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
//            }
//            label = a.getText(com.android.internal.R.styleable.Keyboard_Key_keyLabel);
//            text = a.getText(com.android.internal.R.styleable.Keyboard_Key_keyOutputText);
//
//            if (codes == null && !TextUtils.isEmpty(label))
//            {
//                codes = new int[]{label.charAt(0)};
//            }
//            a.recycle();
//        }
//
//        /**
//         * Informs the key that it has been pressed, in case it needs to change its appearance or
//         * state.
//         *
//         * @see #onReleased(boolean)
//         */
//        public void onPressed()
//        {
//            pressed = !pressed;
//        }
//
//        /**
//         * Changes the pressed state of the key. If it is a sticky key, it will also change the
//         * toggled state of the key if the finger was release inside.
//         *
//         * @param inside whether the finger was released inside the key
//         * @see #onPressed()
//         */
//        public void onReleased(boolean inside)
//        {
//            pressed = !pressed;
//            if (sticky)
//            {
//                on = !on;
//            }
//        }
//
//        int[] parseCSV(String value)
//        {
//            int count = 0;
//            int lastIndex = 0;
//            if (value.length() > 0)
//            {
//                count++;
//                while ((lastIndex = value.indexOf(",", lastIndex + 1)) > 0)
//                {
//                    count++;
//                }
//            }
//            int[] values = new int[count];
//            count = 0;
//            StringTokenizer st = new StringTokenizer(value, ",");
//            while (st.hasMoreTokens())
//            {
//                try
//                {
//                    values[count++] = Integer.parseInt(st.nextToken());
//                } catch (NumberFormatException nfe)
//                {
//                    Log.e(TAG, "Error parsing keycodes " + value);
//                }
//            }
//            return values;
//        }
//
//        /**
//         * Detects if a point falls inside this key.
//         *
//         * @param x the x-coordinate of the point
//         * @param y the y-coordinate of the point
//         * @return whether or not the point falls inside the key. If the key is attached to an edge,
//         * it will assume that all points between the key and the edge are considered to be inside
//         * the key.
//         */
//        public boolean isInside(int x, int y)
//        {
//            boolean leftEdge = (edgeFlags & EDGE_LEFT) > 0;
//            boolean rightEdge = (edgeFlags & EDGE_RIGHT) > 0;
//            boolean topEdge = (edgeFlags & EDGE_TOP) > 0;
//            boolean bottomEdge = (edgeFlags & EDGE_BOTTOM) > 0;
//            if ((x >= this.x || (leftEdge && x <= this.x + this.width))
//                    && (x < this.x + this.width || (rightEdge && x >= this.x))
//                    && (y >= this.y || (topEdge && y <= this.y + this.height))
//                    && (y < this.y + this.height || (bottomEdge && y >= this.y)))
//            {
//                return true;
//            } else
//            {
//                return false;
//            }
//        }
//
//        /**
//         * Returns the square of the distance between the center of the key and the given point.
//         *
//         * @param x the x-coordinate of the point
//         * @param y the y-coordinate of the point
//         * @return the square of the distance of the point from the center of the key
//         */
//        public int squaredDistanceFrom(int x, int y)
//        {
//            int xDist = this.x + width / 2 - x;
//            int yDist = this.y + height / 2 - y;
//            return xDist * xDist + yDist * yDist;
//        }
//
//        /**
//         * Returns the drawable state for the key, based on the current state and type of the key.
//         *
//         * @return the drawable state of the key.
//         * @see android.graphics.drawable.StateListDrawable#setState(int[])
//         */
//        public int[] getCurrentDrawableState()
//        {
//            int[] states = KEY_STATE_NORMAL;
//
//            if (on)
//            {
//                if (pressed)
//                {
//                    states = KEY_STATE_PRESSED_ON;
//                } else
//                {
//                    states = KEY_STATE_NORMAL_ON;
//                }
//            } else
//            {
//                if (sticky)
//                {
//                    if (pressed)
//                    {
//                        states = KEY_STATE_PRESSED_OFF;
//                    } else
//                    {
//                        states = KEY_STATE_NORMAL_OFF;
//                    }
//                } else
//                {
//                    if (pressed)
//                    {
//                        states = KEY_STATE_PRESSED;
//                    }
//                }
//            }
//            return states;
//        }
//    }

}   /////////////END KeyView
