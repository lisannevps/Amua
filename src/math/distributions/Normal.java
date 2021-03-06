/**
 * Amua - An open source modeling framework.
 * Copyright (C) 2017 Zachary J. Ward
 *
 * This file is part of Amua. Amua is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * Amua is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Amua.  If not, see <http://www.gnu.org/licenses/>.
 */

package math.distributions;

import math.MathUtils;
import math.Numeric;
import math.NumericException;

import org.apache.commons.math3.distribution.NormalDistribution;

public final class Normal{
	
	public static Numeric pdf(Numeric params[]) throws NumericException{
		double x=params[0].getDouble(), mu=params[1].getDouble(), sigma=params[2].getDouble();
		if(sigma<=0){throw new NumericException("σ should be >0","Norm");}
		NormalDistribution norm=new NormalDistribution(null,mu,sigma);
		return(new Numeric(norm.density(x)));
	}

	public static Numeric cdf(Numeric params[]) throws NumericException{
		double x=params[0].getDouble(), mu=params[1].getDouble(), sigma=params[2].getDouble();
		if(sigma<=0){throw new NumericException("σ should be >0","Norm");}
		NormalDistribution norm=new NormalDistribution(null,mu,sigma);
		return(new Numeric(norm.cumulativeProbability(x)));
	}	
	
	public static Numeric quantile(Numeric params[]) throws NumericException{
		double x=params[0].getProb(), mu=params[1].getDouble(), sigma=params[2].getDouble();
		if(sigma<=0){throw new NumericException("σ should be >0","Norm");}
		NormalDistribution norm=new NormalDistribution(null,mu,sigma);
		return(new Numeric(norm.inverseCumulativeProbability(x)));
	}
	
	public static Numeric mean(Numeric params[]) throws NumericException{
		double mu=params[0].getDouble(), sigma=params[1].getDouble();
		if(sigma<=0){throw new NumericException("σ should be >0","Norm");}
		return(new Numeric(mu));
	}
	
	public static Numeric variance(Numeric params[]) throws NumericException{
		double sigma=params[1].getDouble();
		if(sigma<=0){throw new NumericException("σ should be >0","Norm");}
		return(new Numeric(sigma*sigma));
	}

	public static Numeric sample(Numeric params[], double rand) throws NumericException{
		if(params.length==2){
			double mu=params[0].getDouble(), sigma=params[1].getDouble();
			if(sigma<=0){throw new NumericException("σ should be >0","Norm");}
			NormalDistribution norm=new NormalDistribution(null,mu,sigma);
			return(new Numeric(norm.inverseCumulativeProbability(rand)));
		}
		else{throw new NumericException("Incorrect number of parameters","Norm");}
	}
	
	public static String description(){
		String des="<html><b>Normal Distribution</b><br>";
		des+="Canonical bell-shaped distribution<br><br>";
		des+="<i>Parameters</i><br>";
		des+=MathUtils.consoleFont("μ")+": Mean<br>";
		des+=MathUtils.consoleFont("σ")+": Standard deviation ("+MathUtils.consoleFont(">0") +")<br>";
		des+="<br><i>Sample</i><br>";
		des+=MathUtils.consoleFont("<b>Norm</b>","green")+MathUtils.consoleFont("(μ,σ,<b><i>~</i></b>)")+": Returns a random variable (mean in base case) from the Normal distribution. Real number<br>";
		des+="<br><i>Distribution Functions</i><br>";
		des+=MathUtils.consoleFont("<b>Norm</b>","green")+MathUtils.consoleFont("(x,μ,σ,<b><i>f</i></b>)")+": Returns the value of the Normal PDF at "+MathUtils.consoleFont("x")+"<br>";
		des+=MathUtils.consoleFont("<b>Norm</b>","green")+MathUtils.consoleFont("(x,μ,σ,<b><i>F</i></b>)")+": Returns the value of the Normal CDF at "+MathUtils.consoleFont("x")+"<br>";
		des+=MathUtils.consoleFont("<b>Norm</b>","green")+MathUtils.consoleFont("(x,μ,σ,<b><i>Q</i></b>)")+": Returns the quantile (inverse CDF) of the Normal distribution at "+MathUtils.consoleFont("x")+"<br>";
		des+="<i><br>Moments</i><br>";
		des+=MathUtils.consoleFont("<b>Norm</b>","green")+MathUtils.consoleFont("(μ,σ,<b><i>E</i></b>)")+": Returns the mean of the Normal distribution<br>";
		des+=MathUtils.consoleFont("<b>Norm</b>","green")+MathUtils.consoleFont("(μ,σ,<b><i>V</i></b>)")+": Returns the variance of the Normal distribution<br>";
		des+="</html>";
		return(des);
	}
}