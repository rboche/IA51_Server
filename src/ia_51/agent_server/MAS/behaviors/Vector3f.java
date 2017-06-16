package ia_51.agent_server.MAS.behaviors;

/*
 * Copyright 1997-2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 *
 */




/**
 * A 3-element vector that is represented by single-precision floating point
 * x,y,z coordinates.  If this value represents a normal, then it should
 * be normalized.
 *
 */
public class Vector3f extends Tuple3f implements java.io.Serializable {

    // Combatible with 1.1
    static final long serialVersionUID = -7031930069184524614L;

    /**
     * Constructs and initializes a Vector3f from the specified xyz coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector3f(float x, float y, float z)
    {
        super(x,y,z);
    }


    /**
     * Constructs and initializes a Vector3f from the array of length 3.
     * @param v the array of length 3 containing xyz in order
     */
    public Vector3f(float[] v)
    {
       super(v);
    }


    /**
     * Constructs and initializes a Vector3f from the specified Vector3f.
     * @param v1 the Vector3f containing the initialization x y z data
     */
    public Vector3f(Vector3f v1)
    {
       super(v1);
    }


    /**
     * Constructs and initializes a Vector3f from the specified Vector3d.
     * @param v1 the Vector3d containing the initialization x y z data
     */
    public Vector3f(Vector3d v1)
    {
       super(v1);
    }


    /**
     * Constructs and initializes a Vector3f from the specified Tuple3f.
     * @param t1 the Tuple3f containing the initialization x y z data
     */
    public Vector3f(Tuple3f t1) {
       super(t1);
    }


    /**
     * Constructs and initializes a Vector3f from the specified Tuple3d.
     * @param t1 the Tuple3d containing the initialization x y z data
     */
    public Vector3f(Tuple3d t1) {
       super(t1);
    }


    /**
     * Constructs and initializes a Vector3f to (0,0,0).
     */
    public Vector3f()
    {
        super();
    }


   /**
     * Returns the squared length of this vector.
     * @return the squared length of this vector
     */
    public final float lengthSquared()
    {
        return (this.x*this.x + this.y*this.y + this.z*this.z);
    }

    /**
     * Returns the length of this vector.
     * @return the length of this vector
     */
    public final float length()
    {
        return (float)
             Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z);
    }


  /**
     * Sets this vector to be the vector cross product of vectors v1 and v2.
     * @param v1 the first vector
     * @param v2 the second vector
     */
    public final Vector3f cross(Vector3f v2)
    {
        float x,y,z;

        x = this.y*v2.z - this.z*v2.y;
        y = v2.x*this.z - v2.z*this.x;
        z = this.x*v2.y - this.y*v2.x;
        return(new Vector3f(x,y,z));
    }

 /**
   * Computes the dot product of this vector and vector v1.
   * @param v1 the other vector
   * @return the dot product of this vector and v1
   */
    public final float dot(Vector3f v1)
      {
        return (this.x*v1.x + this.y*v1.y + this.z*v1.z);
      }

    
    public final Vector3f operator_time(Float a)
    {
        return (new Vector3f(this.x*a, this.y*a, this.z*a));
    }
    
    public final Vector3f operator_plus(Vector3f a)
    {
        return (new Vector3f(this.x+a.x, this.y+a.y, this.z+a.z));
    }
   /**
     * Sets the value of this vector to the normalization of vector v1.
     * @param v1 the un-normalized vector
     */
    public final void normalize(Vector3f v1)
    {
        float norm;

        norm = (float) (1.0/Math.sqrt(v1.x*v1.x + v1.y*v1.y + v1.z*v1.z));
        this.x = v1.x*norm;
        this.y = v1.y*norm;
        this.z = v1.z*norm;
    }

    /**
     * Normalizes this vector in place.
     */
    public final void normalize()
    {
        float norm;

        norm = (float)(1.0/Math.sqrt(this.x*this.x + this.y*this.y + this.z*this.z));
        this.x *= norm;
        this.y *= norm;
        this.z *= norm;
    }

    public final float getOwnOrientation()
    {
        Vector3f a = new Vector3f(0,0,10);
        
        float fCrossX, fCrossY, fCrossZ, fCross, fDot;
        fCrossX = a.y * this.z - a.z * this.y;
        fCrossY = a.z * this.x - a.x * this.z;
        fCrossZ = a.x * this.y - a.y * this.x;

        fCross = (float) Math.sqrt((fCrossX * fCrossX) + (fCrossY * fCrossY) + (fCrossZ *fCrossZ));
        fDot = a.x * this.x + a.y * this.y+ a.z * this.z;
        float fa =  (float) Math.atan2(fCross, fDot);
        float fin = (fa*180)/((float)Math.PI);
        return fin;
    }
  /**
    *   Returns the angle in radians between this vector and the vector
    *   parameter; the return value is constrained to the range [0,PI].
    *   @param v1    the other vector
    *   @return   the angle in radians in the range [0,PI]
    */
   public final float angle(Vector3f v1)
   {
      double vDot = this.dot(v1) / ( this.length()*v1.length() );
      if( vDot < -1.0) vDot = -1.0;
      if( vDot >  1.0) vDot =  1.0;
      return((float) (Math.acos( vDot )));
   }


	public Float signedAngle(Vector3f a, Vector3f b) 
        {
            a.y = a.x;
            a.z = 0f;
            float fCrossX, fCrossY, fCrossZ, fCross, fDot;
            fCrossX = a.y * b.z - a.z * b.y;
            fCrossY = a.z * b.x - a.x * b.z;
            fCrossZ = a.x * b.y - a.y * b.x;

            fCross = (float) Math.sqrt((fCrossX * fCrossX) + (fCrossY * fCrossY) + (fCrossZ *fCrossZ));
            fDot = a.x * b.x + a.y * b.y + a.z * b.z;
            
            float xa = (float) Math.atan2(fCross, fDot);
            float xb = (xa*180)/((float)Math.PI);
            return xb;
	}
        
	public final void setLength(float speed){
    	if(speed>=0.0f){
    		float norm = (float) Math.sqrt(this.x*this.x + this.y*this.y+ this.z*this.z);
    		if(norm !=0.0f){
	    		norm = speed/norm;
	    		this.x*=norm;
	    		this.y*=norm;
	    		this.z*=norm;
	    	}else{
	    		this.x = speed;
	    		this.y = 0.0f;
	    		this.z = 0.0f;
	    	}
    	}else{
    		this.x = this.y = this.z = 0.0f;
    	}
    }
	
        
	public Vector3f toColinearVector(final float length) {
        Vector3f _xblockexpression = null;
        {
          float x = 0;
          float y = 0;
          float z = 0;
          if ((length >= 0f)) {
            float norm = ((this.x * this.x) + (this.y * this.y)+ (this.z * this.z));
            if ((norm != 0f)) {
              double _sqrt = Math.sqrt(norm);
              norm = ((float) _sqrt);
              norm = (length / norm);
              x = (this.x * norm);
              y = (this.y * norm);
              z = (this.z * norm);
            } else {
              x = length;
              y = 0f;
              z = 0f;
            }
          } else {
            x = (y = z = 0f);
          }
          _xblockexpression = new Vector3f(x, y, z);
        }
        return _xblockexpression;
    }
	
	public final Vector3f add(Vector3f seriousVector){
    	Vector3f oui = null;
    	oui.x = this.x+seriousVector.x;
    	oui.y = this.y +seriousVector.y;
    	oui.z = this.z + seriousVector.z;
      
    	return oui; 
    }
        
        

}