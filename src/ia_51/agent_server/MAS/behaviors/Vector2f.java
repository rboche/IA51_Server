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
 * A 2-element vector that is represented by single-precision floating
 * point x,y coordinates.
 *
 */
public class Vector2f extends Tuple2f implements java.io.Serializable {

    // Combatible with 1.1
    static final long serialVersionUID = -2168194326883512320L;

    /**
     * Constructs and initializes a Vector2f from the specified xy coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector2f(float x, float y)
    {
      super(x,y);
    }


    /**
     * Constructs and initializes a Vector2f from the specified array.
     * @param v the array of length 2 containing xy in order
     */
    public Vector2f(float[] v)
    {
      super(v);
    }


    /**
     * Constructs and initializes a Vector2f from the specified Vector2f.
     * @param v1 the Vector2f containing the initialization x y data
     */
    public Vector2f(Vector2f v1)
    {
       super(v1);
    }


    /**
     * Constructs and initializes a Vector2f from the specified Vector2d.
     * @param v1 the Vector2d containing the initialization x y data
     */
    public Vector2f(Vector2d v1)
    {
       super(v1);
    }


    /**
     * Constructs and initializes a Vector2f from the specified Tuple2f.
     * @param t1 the Tuple2f containing the initialization x y data
     */
    public Vector2f(Tuple2f t1)
    {
       super(t1);
    }


    /**
     * Constructs and initializes a Vector2f from the specified Tuple2d.
     * @param t1 the Tuple2d containing the initialization x y data
     */
    public Vector2f(Tuple2d t1)
    {
       super(t1);
    }



    /**
     * Constructs and initializes a Vector2f to (0,0).
     */
    public Vector2f()
    {
        super();
    }


  /**
   * Computes the dot product of the this vector and vector v1.
   * @param v1 the other vector
   */
  public final float dot(Vector2f v1)
    {
      return (this.x*v1.x + this.y*v1.y );
    }


    /**
     * Returns the length of this vector.
     * @return the length of this vector
     */
    public final float length()
    {
        return (float) Math.sqrt(this.x*this.x + this.y*this.y);
    }
    
    
    
    public final Vector2f add(Vector2f seriousVector){
    	Vector2f oui = null;
    	oui.x = this.x+seriousVector.x;
    	oui.y = this.y +seriousVector.y;
      
    	return oui; 
    }
    
    public final float signedAngle(Vector2f a, Vector2f b){
		float x1, y1, x2 , y2;
		x1 = a.x;
		y1 = a.y;
		x2 = b.x;
		y2 = b.y;
		double _sqrt = Math.sqrt(((x1 * x1) + (y1 * y1)));
	    float length1 = ((float) _sqrt);
	    double _sqrt_1 = Math.sqrt(((x2 * x2) + (y2 * y2)));
	    float length2 = ((float) _sqrt_1);
	    if (((length1 == 0f) || (length2 == 0f))) {
	      return Float.NaN;
	    }
	    float cx1 = x1;
	    float cy1 = y1;
	    float cx2 = x2;
	    float cy2 = y2;
	    if ((length1 != 1f)) {
	      float _cx1 = cx1;
	      cx1 = (_cx1 / length1);
	      float _cy1 = cy1;
	      cy1 = (_cy1 / length1);
	    }
	    if ((length2 != 1f)) {
	      float _cx2 = cx2;
	      cx2 = (_cx2 / length2);
	      float _cy2 = cy2;
	      cy2 = (_cy2 / length2);
	    }
	    float cos = ((cx1 * cx2) + (cy1 * cy2));
	    float sin = ((cx1 * cy2) - (cy1 * cx2));
	    double _atan2 = Math.atan2(sin, cos);
	    float angle = ((float) _atan2);
	    return angle;

    }
    
    public final void setLength(float speed){
    	if(speed>=0.0f){
    		float norm = (float) Math.sqrt(this.x*this.x + this.y*this.y);
    		if(norm !=0.0f){
	    		norm = speed/norm;
	    		this.x*=norm;
	    		this.y*=norm;
	    	}else{
	    		this.x = speed;
	    		this.y = 0.0f;
	    	}
    	}else{
    		this.x = this.y = 0.0f;
    	}
    }
    /**
     * Returns the squared length of this vector.
     * @return the squared length of this vector
     */
    public final float lengthSquared()
    {
        return (this.x*this.x + this.y*this.y);
    }

    /**
     * Sets the value of this vector to the normalization of vector v1.
     * @param v1 the un-normalized vector
     */
    public final void normalize(Vector2f v1)
    {
        float norm;

        norm = (float) (1.0/Math.sqrt(v1.x*v1.x + v1.y*v1.y));
        this.x = v1.x*norm;
        this.y = v1.y*norm;
    }

    /**
     * Normalizes this vector in place.
     */
    public final void normalize()
    {
        float norm;

        norm = (float)
               (1.0/Math.sqrt(this.x*this.x + this.y*this.y));
        this.x *= norm;
        this.y *= norm;
    }

    public Vector2f toColinearVector(final float length) {
        Vector2f _xblockexpression = null;
        {
          float x = 0;
          float y = 0;
          if ((length >= 0f)) {
            float norm = ((this.x * this.x) + (this.y * this.y));
            if ((norm != 0f)) {
              double _sqrt = Math.sqrt(norm);
              norm = ((float) _sqrt);
              norm = (length / norm);
              x = (this.x * norm);
              y = (this.y * norm);
            } else {
              x = length;
              y = 0f;
            }
          } else {
            x = (y = 0f);
          }
          _xblockexpression = new Vector2f(x, y);
        }
        return _xblockexpression;
    }

  /**
    *   Returns the angle in radians between this vector and the vector
    *   parameter; the return value is constrained to the range [0,PI].
    *   @param v1    the other vector
    *   @return   the angle in radians in the range [0,PI]
    */
   public final float angle(Vector2f v1)
   {
      double vDot = this.dot(v1) / ( this.length()*v1.length() );
      if( vDot < -1.0) vDot = -1.0;
      if( vDot >  1.0) vDot =  1.0;
      return((float) (Math.acos( vDot )));
   }


}