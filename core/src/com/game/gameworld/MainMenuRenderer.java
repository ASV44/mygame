package com.game.gameworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.screens.GameModeScreen;
import com.game.screens.GameScreenMoves;
import com.game.screens.GameScreenTime;
import com.game.shapes.MyGame;

/**
 * Created by hackintosh on 11/27/16.
 */

public class MainMenuRenderer {
    private Game game;
    private SpriteBatch batch;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private Stage stage;
    private ImageButton timeAttack;
    private ImageButton countMoves;
    private ImageButton exit;
    private Drawable drawable;
    private Texture buttonTexture;
    private ImageButton time1;
    private Texture background;

    public MainMenuRenderer(final MyGame game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("menu.png"));
        batch = new SpriteBatch();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("good_time.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = (int) (0.045 * Gdx.graphics.getHeight());
        font = generator.generateFont(parameter);
        generator.dispose();
        stage = new Stage();
        buttonTexture = new Texture(Gdx.files.internal("menu.png"));
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,158,507,755,481));
        countMoves = new ImageButton(drawable);
        countMoves.setBounds((float) 0.146 * Gdx.graphics.getWidth(), (float) 0.485 * Gdx.graphics.getHeight(),(float) 0.699 * Gdx.graphics.getWidth(), (float) 0.25 * Gdx.graphics.getHeight());
        countMoves.addListener(new ActorGestureListener() {
            @Override
            public  void tap(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Button_count_moves","works ");
                game.setScreenMoves(new GameScreenMoves(game, "Moves"));
                game.setScreen(game.getScreenMoves());
            }
        });
        stage.addActor(countMoves);
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,197,1173,672,194));
        timeAttack = new ImageButton(drawable);
        timeAttack.setBounds((float) 0.182 * Gdx.graphics.getWidth(), (float) 0.288 * Gdx.graphics.getHeight(), (float) 0.622 * Gdx.graphics.getWidth(), (float) 0.101 * Gdx.graphics.getHeight());
        timeAttack.addListener(new ActorGestureListener(){
            @Override
            public  void tap(InputEvent event, float x, float y, int count, int button) {
                Gdx.app.log("Button_time_attack","works ");
                game.setScreenMode(new GameModeScreen(game));
                game.setScreen(game.getModeScreen());
                //return true;
            }
        });
        stage.addActor(timeAttack);
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,415,1603,246,156));
        exit = new ImageButton(drawable);
        exit.setBounds((float) 0.384 * Gdx.graphics.getWidth(), (float) 0.084375 * Gdx.graphics.getHeight(), (float) 0.227 * Gdx.graphics.getWidth(), (float) 0.08125 * Gdx.graphics.getHeight());
        exit.addListener(new ActorGestureListener() {
            @Override
            public  void tap(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log("Button_exit","works ");
                Gdx.app.exit();
            }
        });
        stage.addActor(exit);
        drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture,330,247,345,115));
        time1 = new ImageButton(drawable);
        time1.setBounds((float) 0.35 * Gdx.graphics.getWidth(), (float) 0.45 * Gdx.graphics.getHeight(), (float) 0.319 * Gdx.graphics.getWidth(), (float) 0.059 * Gdx.graphics.getHeight());
        time1.addListener(new ActorGestureListener() {
            @Override
            public  void tap(InputEvent event, float x, float y, int count, int button) {
                Gdx.app.log("Button_time1","works ");
                game.setScreenMoves(new GameScreenMoves(game, "Time1"));
                game.setScreen(game.getScreenMoves());
                //return true;
            }
        });
        //Gdx.input.setInputProcessor(stage);
        //stage.addActor(time1);
        game.getInputMultiplexer().addProcessor(stage);
    }

    public void render() {
        Gdx.gl.glClearColor(255, 255, 255, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw();
        //Gdx.input.setInputProcessor(stage);

    }

    public Stage getStage() { return stage; }
}