package com.game.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.screens.GameScreenTime;
import com.game.shapes.MyGame;

import java.text.DecimalFormat;

/**
 * Created by hackintosh on 11/17/16.
 */

public class GameOverRenderer {
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private FrameBuffer fbo;
    private Texture restartground;
    private Texture foreground;
    //private Game game;
    private Color c;
    private String gameMode;
    private String finalMessage;
    private Texture buttonTexture;
    private Drawable drawable;
    private ImageButton restart;
    private ImageButton menu;
    private ImageButton vibrate;
    private ImageButton back;
    private MyGame game;
    private Stage stage;

    public GameOverRenderer(final MyGame game, SpriteBatch batch, final String gameMode) {
        this.game = game;
        this.batch = batch;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("good_time.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = (int) (0.03 * Gdx.graphics.getHeight());;
        font = generator.generateFont(parameter);
        generator.dispose();
        foreground = new Texture(Gdx.files.internal("foreground.png"));
        this.gameMode = gameMode;
        stage = new Stage();
        buttonTexture = new Texture(Gdx.files.internal("gameOver.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,745,727,245,151));
        restart = new ImageButton(drawable);
        restart.setBounds((float) 0.09 * Gdx.graphics.getWidth(), (float) 0.225 * Gdx.graphics.getHeight(),(float) 0.231 * Gdx.graphics.getWidth(), (float) 0.082 * Gdx.graphics.getHeight());
        restart.addListener(new ActorGestureListener() {
            @Override
            public  void tap(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Button_restart","works ");
                if(gameMode.equals("Time")) {
                    game.getScreenTime().getItems().setGameState("Active");
                    game.getScreenTime().getItems().Restart();
                }
                if(gameMode.equals("Moves") || gameMode.equals("Time1")) {
                    game.getScreenMoves().getItems().setGameState("Active");
                    game.getScreenMoves().getItems().Restart();
                }
            }
        });
        stage.addActor(restart);
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,100,725,245,151));
        menu = new ImageButton(drawable);
        menu.setBounds((float) 0.692 * Gdx.graphics.getWidth(), (float) 0.225 * Gdx.graphics.getHeight(),(float) 0.231 * Gdx.graphics.getWidth(), (float) 0.082 * Gdx.graphics.getHeight());
        menu.addListener(new ActorGestureListener() {
            @Override
            public  void tap(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Button_menu","works ");
                game.setScreen(game.getMenuScreen());
                game.getInputMultiplexer().addProcessor(game.getMenuScreen().getStage());
            }
        });
        stage.addActor(menu);
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,420,725,243,151));
        vibrate = new ImageButton(drawable);
        vibrate.setBounds((float) 0.388 * Gdx.graphics.getWidth(), (float) 0.225 * Gdx.graphics.getHeight(),(float) 0.231 * Gdx.graphics.getWidth(), (float) 0.082 * Gdx.graphics.getHeight());
        vibrate.addListener(new ActorGestureListener() {
            @Override
            public  void tap(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Button_menu","works ");
                game.setScreen(game.getMenuScreen());
                game.getInputMultiplexer().addProcessor(game.getMenuScreen().getStage());
            }
        });
        stage.addActor(vibrate);
        game.getInputMultiplexer().addProcessor(stage);
    }

    public void  render() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        batch.begin();
        //restartground
        //seaTexture = new Texture(px);
        //Color c = batch.getColor();
        //batch.setColor(c.r, c.g, c.b, 1f); //set alpha to 1
        //batch.draw(restartground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //foreground
        c = batch.getColor();
        batch.setColor(c.r, c.g, c.b, .5f);//set alpha to 0.3
        batch.draw(foreground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Color c = batch.getColor();
        batch.setColor(c.r, c.g, c.b, 1f); //set alpha to 1
        if( gameMode.equals("Time")) {
            finalMessage = "Time is Over !";
            font.draw(batch, finalMessage,(float) 0.277 * Gdx.graphics.getWidth(), (float) 0.52 * Gdx.graphics.getHeight());
            font.draw(batch, "Your Score is:", (float) 0.277 * Gdx.graphics.getWidth(), (float) 0.45 * Gdx.graphics.getHeight());
            font.draw(batch, "" + game.getScreenTime().getItems().getScore(), (float) 0.5 * Gdx.graphics.getWidth(), (float) 0.38 * Gdx.graphics.getHeight());
        }
        if( gameMode.equals("Moves")) {
            if(game.getScreenMoves().getItems().getScore() <= 0) {
                finalMessage = "Game Over!";
                font.draw(batch, finalMessage,(float) 0.33 * Gdx.graphics.getWidth(), (float) 0.65 * Gdx.graphics.getHeight());
                finalMessage = "Try one more time";
                font.draw(batch, finalMessage,(float) 0.18 * Gdx.graphics.getWidth(), (float) 0.57 * Gdx.graphics.getHeight());
            }
            else {
                finalMessage = "Excellent !";
                font.draw(batch, finalMessage, (float) 0.33 * Gdx.graphics.getWidth(), (float) 0.65 * Gdx.graphics.getHeight());
                finalMessage = "You have combined";
                font.draw(batch, finalMessage, (float) 0.18 * Gdx.graphics.getWidth(), (float) 0.57 * Gdx.graphics.getHeight());
                finalMessage = "all Shapes";
                font.draw(batch, finalMessage, (float) 0.32 * Gdx.graphics.getWidth(), (float) 0.52 * Gdx.graphics.getHeight());
                font.draw(batch, "Your Score is:", (float) 0.28 * Gdx.graphics.getWidth(), (float) 0.45 * Gdx.graphics.getHeight());
                font.draw(batch, "" + game.getScreenMoves().getItems().getScore(), (float) 0.45 * Gdx.graphics.getWidth(), (float) 0.38 * Gdx.graphics.getHeight());
            }
        }
        if(gameMode.equals("Time1")) {
            finalMessage = "Excellent !";
            font.draw(batch, finalMessage, (float) 0.33 * Gdx.graphics.getWidth(), (float) 0.65 * Gdx.graphics.getHeight());
            finalMessage = "You have combined";
            font.draw(batch, finalMessage, (float) 0.18 * Gdx.graphics.getWidth(), (float) 0.57 * Gdx.graphics.getHeight());
            finalMessage = "all Shapes";
            font.draw(batch, finalMessage, (float) 0.32 * Gdx.graphics.getWidth(), (float) 0.52 * Gdx.graphics.getHeight());
            font.draw(batch, "In: " +  String.valueOf(decimalFormat.format(game.getScreenMoves().getItems().getTime() ) ) + " sec" , (float) 0.28 * Gdx.graphics.getWidth(), (float) 0.45 * Gdx.graphics.getHeight());
        }

        batch.end();
        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();
    }

    public Stage getStage() { return this.stage; }
}