package in.softsolutionzone.util;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CustomResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5714799134935088290L;

	@JsonInclude(Include.NON_NULL)
	private T status;
	@JsonInclude(Include.NON_NULL)
	private T message;

	@JsonInclude(Include.NON_NULL)
	@JsonProperty("data")
	private T payload;

	public CustomResponse(CustomResponseBuilder<T> customResponseBuilder) {
		super();
		this.payload = customResponseBuilder.payload;
		this.status=customResponseBuilder.status;
		this.message=customResponseBuilder.message;

	}

	public static class CustomResponseBuilder<T> {

		private T payload;
		private T status;
		private T message;

		public CustomResponseBuilder<T> setStatus(T status) {
			this.status = status;
			return this;
		}

		public CustomResponseBuilder<T> setMessage(T message) {
			this.message = message;
			return this;
		}

		public CustomResponseBuilder<T> setPayload(T payload) {
			this.payload = payload;
			return this;
		}

		public CustomResponse<T> build() {
			return new CustomResponse<T>(this);
		}
	}

}