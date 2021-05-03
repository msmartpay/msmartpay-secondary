package in.softsolutionzone.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ICICICollectionResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714799134935088290L;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("SuccessANDRejected")
	private T successANDRejected;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("CODE")
	private T code;


	public ICICICollectionResponse(ICICICollectionResponseBuilder<T> customResponseBuilder) {
		super();
		this.code=customResponseBuilder.code;
		this.successANDRejected=customResponseBuilder.successANDRejected;

	}

	public static class ICICICollectionResponseBuilder<T> {

		private T code;
		private T successANDRejected;

		public ICICICollectionResponseBuilder<T> setCode(T code) {
			this.code = code;
			return this;
		}

		public ICICICollectionResponseBuilder<T> setSuccessANDRejected(T successANDRejected) {
			this.successANDRejected = successANDRejected;
			return this;
		}


		public ICICICollectionResponse<T> build() {
			return new ICICICollectionResponse<T>(this);
		}
	}

}