package ia_51.agent_server.MAS.behaviors;

import java.util.Vector;


class BehaviorOutputBis{
	
	Vector3f linearMotion = new Vector3f();
	Float angularMotion = 0.0f;
	
	public BehaviorOutputBis(Vector3f linearMotionInput){
		linearMotion = linearMotionInput;
	}

	public BehaviorOutputBis(Float angularMotionInput){
		angularMotion = angularMotionInput;
	}
	public BehaviorOutputBis(Vector3f linearMotionInput, Float angularMotionInput){
		linearMotion = linearMotionInput;
		angularMotion = angularMotionInput;
	}

	public BehaviorOutputBis operator_plus(BehaviorOutputBis motion){
		Vector3f lin = this.linearMotion.add(motion.linearMotion);
		Float ang = this.angularMotion + motion.angularMotion;
		return new BehaviorOutputBis(lin,ang);
	}

	public void operator_add(BehaviorOutputBis motion){
		this.linearMotion = this.linearMotion.add(motion.linearMotion);
		this.angularMotion+= motion.angularMotion;
	}
}