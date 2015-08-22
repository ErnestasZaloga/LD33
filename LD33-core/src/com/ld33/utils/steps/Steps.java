package com.ld33.utils.steps;

import com.badlogic.gdx.math.Interpolation;
import com.ld33.utils.steps.scene.StepAction;

abstract public class Steps {
	
	public static StepAction action(final Step step) {
		return StepAction.obtain(step);
	}
	
	public static StepAction action(final float delay,
							 		final Step step) {
		
		return StepAction.obtain(delay(delay, step));
	}
	
	public static <T extends Step> T step(final Class<T> type) {
		return Step.obtain(type);
	}
	
	public static DelayStep delay() {
		return DelayStep.obtain();
	}
	
	public static DelayStep delay(final float duration) {
		return DelayStep.obtain(duration);
	}
	
	public static DelayStep delay(final float duration, 
								  final Step step) {
		
		return DelayStep.obtain(duration, step);
	}
	
	public static DeltaScaleStep delta() {
		return DeltaScaleStep.obtain();
	}
	
	public static DeltaScaleStep delta(final float scale, 
								  final Step step) {
		
		return DeltaScaleStep.obtain(scale, step);
	}
	
	public static FloatStep _float() {
		return FloatStep.obtain();
	}
	
	public static FloatStep _float(final float end,
								   final FloatStep.Listener listener) {
		
		return FloatStep.obtain(end, listener);
	}
	
	public static FloatStep _float(final float start, 
								   final float end, 
								   final float duration,
								   final FloatStep.Listener listener) {
		
		return FloatStep.obtain(start, end, duration, listener);
	}
	
	public static FloatStep _float(final float start, 
								   final float end, 
								   final float duration, 
								   final Interpolation interpolation,
								   final FloatStep.Listener listener) {
		
		return FloatStep.obtain(start, end, duration, interpolation, listener);
	}

	public static IntStep _int() {
		return IntStep.obtain();
	}

	public static IntStep _int(final int end,
							   final IntStep.Listener listener) {
		
		return IntStep.obtain(end, listener);
	}
	
	public static IntStep _int(final int start, 
							   final int end, 
							   final float duration,
							   final IntStep.Listener listener) {
		
		return IntStep.obtain(start, end, duration, listener);
	}
	
	public static IntStep _int(final int start, 
							   final int end, 
							   final float duration, 
							   final Interpolation interpolation,
							   final IntStep.Listener listener) {
		
		return IntStep.obtain(start, end, duration, interpolation, listener);
	}

	public static ParallelStep parallel() {
		return ParallelStep.obtain();
	}

	public static ParallelStep parallel(final Step step1) {
		return ParallelStep.obtain(step1);
	}

	public static ParallelStep parallel(final Step step1, 
								 		final Step step2) {
		
		return ParallelStep.obtain(step1, step2);
	}

	public static ParallelStep parallel(final Step step1, 
										final Step step2,
										final Step step3) {
		
		return ParallelStep.obtain(step1, step2, step3);
	}

	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4) {
		
		return ParallelStep.obtain(step1, step2, step3, step4);
	}
	
	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5) {
		
		return ParallelStep.obtain(step1, step2, step3, step4, step5);
	}
	
	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6) {
		
		return ParallelStep.obtain(step1, step2, step3, step4, step5, step6);
	}
	
	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7) {
		
		return ParallelStep.obtain(step1, step2, step3, step4, step5, step6, step7);
	}
	
	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7,
								 		final Step step8) {
		
		return ParallelStep.obtain(step1, step2, step3, step4, step5, step6, step7, step8);
	}
	
	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7,
								 		final Step step8,
								 		final Step step9) {
		
		return ParallelStep.obtain(step1, step2, step3, step4, step5, step6, step7, step8, step9);
	}
	
	public static ParallelStep parallel(final Step step1, 
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7,
								 		final Step step8,
								 		final Step step9,
								 		final Step step10) {
		
		return ParallelStep.obtain(step1, step2, step3, step4, step5, step6, step7, step8, step9, step10);
	}
	
	@Deprecated
	/**
	 * Will create garbage!
	 * */
	public static ParallelStep parallel(final Step... steps) {
		return ParallelStep.obtain(steps);
	}

	public static RepeatStep repeat() {
		return RepeatStep.obtain();
	}
	
	public static RepeatStep repeat(final Step step) {
		return RepeatStep.obtain(step);
	}
	
	public static RepeatStep repeat(final int repeatCount,
									final Step step) {
		
		return RepeatStep.obtain(repeatCount, step);
	}

	public static RunStep run() {
		return RunStep.obtain();
	}
	
	public static RunStep run(final Runnable runnable) {
		return RunStep.obtain(runnable);
	}
	
	public static SequenceStep sequence() {
		return SequenceStep.obtain();
	}
	
	public static SequenceStep sequence(final Step step1) {
		return SequenceStep.obtain(step1);
	}
	
	public static SequenceStep sequence(final Step step1,
									    final Step step2) {
		
		return SequenceStep.obtain(step1, step2);
	}
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3) {
		
		return SequenceStep.obtain(step1, step2, step3);
	}
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4) {
		
		return SequenceStep.obtain(step1, step2, step3, step4);
	}
	
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5) {
		
		return SequenceStep.obtain(step1, step2, step3, step4, step5);
	}
	
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6) {
		
		return SequenceStep.obtain(step1, step2, step3, step4, step5, step6);
	}
	
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7) {
		
		return SequenceStep.obtain(step1, step2, step3, step4, step5, step6, step7);
	}
	
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7,
								 		final Step step8) {
		
		return SequenceStep.obtain(step1, step2, step3, step4, step5, step6, step7, step8);
	}
	
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7,
								 		final Step step8,
								 		final Step step9) {
		
		return SequenceStep.obtain(step1, step2, step3, step4, step5, step6, step7, step8, step9);
	}
	
	
	public static SequenceStep sequence(final Step step1,
								 		final Step step2,
								 		final Step step3,
								 		final Step step4,
								 		final Step step5,
								 		final Step step6,
								 		final Step step7,
								 		final Step step8,
								 		final Step step9,
								 		final Step step10) {
		
		return SequenceStep.obtain(step1, step2, step3, step4, step5, step6, step7, step8, step9, step10);
	}
	
	@Deprecated
	/**
	 * Will create garbage!
	 * */
	public static SequenceStep sequence(final Step... steps) {
		return SequenceStep.obtain(steps);
	}

	public static ChangingDeltaScaleStep timedDelta() {
		return ChangingDeltaScaleStep.obtain();
	}
	
	public static ChangingDeltaScaleStep timedDelta(final float scaleEnd,
													final float duration,
													final Step step) {
		
		return ChangingDeltaScaleStep.obtain(scaleEnd, duration, step);
	}
	
	public static ChangingDeltaScaleStep timedDelta(final float scaleStart,
													final float scaleEnd,
													final float duration,
													final Step step) {
	
		return ChangingDeltaScaleStep.obtain(scaleStart, scaleEnd, duration, step);
	}
	
	public static ChangingDeltaScaleStep timedDelta(final float scaleEnd,
													final float duration, 
													final Interpolation interpolation,
													final Step step) {

		return ChangingDeltaScaleStep.obtain(scaleEnd, duration, interpolation, step);
	}
	
	public static ChangingDeltaScaleStep timedDelta(final float scaleStart,
													final float scaleEnd,
													final float duration, 
													final Interpolation interpolation,
													final Step step) {
		
		return ChangingDeltaScaleStep.obtain(scaleStart, scaleEnd, duration, interpolation, step);
	}
	
	public static WaitStep waitEmpty() {
		return WaitStep.obtain();
	}
	
	public static WaitStep wait(final int wait) {
		return WaitStep.obtain(wait);
	}
	
	public static WaitStep wait(final int wait, 
								final Step step) {
		
		return WaitStep.obtain(wait, step);
	}
	
	private Steps() {}
}
