package com.viking.appchina;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Speaker {
    int color;
    int colorNew;
    Context context;
    Handler handler = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            Speaker.this.imgLevelSound.setVisibility(View.GONE);
            Speaker.this.view.findViewById(R.id.fr_speaker).setVisibility(View.GONE);
            return false;
        }
    });
    int height;
    int heightPlus;
    ImageView imgBgMicro;
    ImageView imgBgMicroBoder;
    ImageView imgLevelSound;
    ImageView imgMicro;
    boolean isVoicing = false;
    AnimatorSet set = new AnimatorSet();
    View view;

    public boolean isVoicing() {
        return this.isVoicing;
    }

    public void setVoicing(boolean z) {
        this.isVoicing = z;
    }

    public Speaker(Context context2, View view2) {
        this.context = context2;
        this.view = view2;
        init();
    }

    private void init() {
        this.imgMicro = (ImageView) this.view.findViewById(R.id.img_micro);
        this.imgBgMicro = (ImageView) this.view.findViewById(R.id.img_bg_micro);
        this.imgLevelSound = (ImageView) this.view.findViewById(R.id.img_level_sound);
        this.imgBgMicroBoder = (ImageView) this.view.findViewById(R.id.img_bg_micro_boder);
        this.height = this.context.getResources().getDimensionPixelSize(R.dimen.height_progress_voice);
        this.heightPlus = this.context.getResources().getDimensionPixelSize(R.dimen.height_line);
    }

    public void setColorDefault(int i) {
        this.color = i;
        this.colorNew = (-1118482 & i) | 1073741824;
        this.imgMicro.setColorFilter(i);
        this.imgLevelSound.setColorFilter(i);
    }

    public void update(double d) {
        int i;
        int i2 = this.height;
        if (d > 7.0d) {
            this.imgLevelSound.setColorFilter(this.color);
        } else if (d > 4.0d) {
            this.imgLevelSound.setColorFilter(UtilFunction.getAlphaColor(this.color, 80));
        } else {
            this.imgLevelSound.setColorFilter(UtilFunction.getAlphaColor(this.color, 60));
        }
        if (d > 9.5d) {
            i = this.height + (this.heightPlus * 80);
        } else if (d > 9.0d) {
            i = this.height + (this.heightPlus * 78);
        } else if (d > 8.5d) {
            i = this.height + (this.heightPlus * 76);
        } else if (d > 8.0d) {
            i = this.height + (this.heightPlus * 74);
        } else if (d > 7.5d) {
            i = this.height + (this.heightPlus * 72);
        } else if (d > 7.0d) {
            i = this.height + (this.heightPlus * 68);
        } else if (d > 6.5d) {
            i = this.height + (this.heightPlus * 64);
        } else if (d > 6.0d) {
            i = this.height + (this.heightPlus * 60);
        } else if (d > 5.5d) {
            i = this.height + (this.heightPlus * 56);
        } else if (d > 5.0d) {
            i = this.height + (this.heightPlus * 52);
        } else if (d > 4.5d) {
            i = this.height + (this.heightPlus * 48);
        } else if (d > 4.0d) {
            i = this.height + (this.heightPlus * 44);
        } else if (d > 3.5d) {
            i = this.height + (this.heightPlus * 40);
        } else if (d > 3.0d) {
            i = this.height + (this.heightPlus * 36);
        } else if (d > 2.5d) {
            i = this.height + (this.heightPlus * 32);
        } else if (d > 2.0d) {
            i = this.height + (this.heightPlus * 28);
        } else if (d > 1.5d) {
            i = this.height + (this.heightPlus * 24);
        } else if (d > 1.0d) {
            i = this.height + (this.heightPlus * 20);
        } else if (d > 0.5d) {
            i = this.height + (this.heightPlus * 16);
        } else if (d > 0.0d) {
            i = this.height + (this.heightPlus * 12);
        } else if (d > -0.5d) {
            this.imgLevelSound.setVisibility(View.VISIBLE);
            i = this.height + (this.heightPlus * 10);
        } else if (d > -1.0d) {
            i = this.height + (this.heightPlus * 8);
        } else if (d > -1.5d) {
            i = this.height + (this.heightPlus * 6);
        } else {
            this.view.setVisibility(View.VISIBLE);
            i = this.height - (this.heightPlus * 2);
        }
        this.imgBgMicro.setColorFilter(this.color);
        this.imgBgMicro.setVisibility(View.VISIBLE);
        this.imgMicro.setColorFilter(-1);
        this.imgLevelSound.getLayoutParams().height = i;
        this.imgLevelSound.getLayoutParams().width = i;
        this.imgLevelSound.requestLayout();
    }

    public void show() {
        this.view.findViewById(R.id.fr_speaker).setVisibility(View.VISIBLE);
        this.imgMicro.setColorFilter(this.color);
        this.imgMicro.setVisibility(View.VISIBLE);
        this.imgBgMicro.setVisibility(View.VISIBLE);
        this.imgBgMicro.setColorFilter(-2);
        this.imgBgMicroBoder.setVisibility(View.VISIBLE);
        this.imgBgMicroBoder.setColorFilter(this.color);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, "translationY", new float[]{(float) UtilFunction.getSreenHeight(this.context, 0.2d), 0.0f}), ObjectAnimator.ofFloat(this.view, "scaleX", new float[]{1.0f, 0.71f}), ObjectAnimator.ofFloat(this.view, "scaleY", new float[]{1.0f, 0.71f})});
        animatorSet.setDuration(1000).start();
    }

    public void initTalking() {
        this.view.findViewById(R.id.fr_speaker).setVisibility(View.VISIBLE);
        this.imgMicro.setVisibility(View.GONE);
        this.imgBgMicro.setVisibility(View.GONE);
        this.imgBgMicro.setColorFilter(-2);
        this.imgBgMicroBoder.setVisibility(View.VISIBLE);
        this.imgBgMicroBoder.setColorFilter(this.color);
        this.imgLevelSound.setVisibility(View.VISIBLE);
        this.imgLevelSound.setColorFilter(UtilFunction.getAlphaColor(this.color, 50));
    }

    public void stop(int i) {
        this.imgBgMicro.setVisibility(View.GONE);
        this.imgMicro.setColorFilter(this.color);
        if (i > 0) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Speaker.this.handler.sendEmptyMessage(0);
                }
            }, (long) i);
            return;
        }
        this.imgLevelSound.setVisibility(View.GONE);
        this.view.findViewById(R.id.fr_speaker).setVisibility(View.GONE);
    }

    public void stop() {
        this.imgMicro.setColorFilter(this.color);
        this.imgMicro.setVisibility(View.VISIBLE);
        this.imgBgMicro.setVisibility(View.VISIBLE);
        this.imgBgMicro.setColorFilter(-2);
        this.imgBgMicroBoder.setVisibility(View.VISIBLE);
        this.imgBgMicroBoder.setColorFilter(this.color);
        this.imgLevelSound.setVisibility(View.GONE);
        setVoicing(false);
    }

    public void moveUp(int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, "translationY", new float[]{0.0f, (float) (-UtilFunction.getSreenHeight(this.context, 0.1d))}), ObjectAnimator.ofFloat(this.view, "scaleX", new float[]{0.71f, 1.0f}), ObjectAnimator.ofFloat(this.view, "scaleY", new float[]{0.71f, 1.0f})});
        animatorSet.setDuration((long) i).start();
    }

    public void moveDown(int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, "translationY", new float[]{(float) (-UtilFunction.getSreenHeight(this.context, 0.1d)), 0.0f}), ObjectAnimator.ofFloat(this.view, "scaleX", new float[]{1.0f, 0.71f}), ObjectAnimator.ofFloat(this.view, "scaleY", new float[]{1.0f, 0.71f})});
        animatorSet.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                Speaker.this.stop();
            }
        });
        animatorSet.setDuration((long) i).start();
    }

    public void hideAnimation(int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, "translationY", new float[]{(float) (-UtilFunction.getSreenHeight(this.context, 0.1d)), 0.0f}), ObjectAnimator.ofFloat(this.view, "scaleX", new float[]{1.0f, 0.71f}), ObjectAnimator.ofFloat(this.view, "scaleY", new float[]{1.0f, 0.71f})});
        animatorSet.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                Speaker.this.stop();
                Speaker.this.view.findViewById(R.id.fr_speaker).setVisibility(View.GONE);
            }
        });
        animatorSet.setDuration((long) i).start();
    }
}
