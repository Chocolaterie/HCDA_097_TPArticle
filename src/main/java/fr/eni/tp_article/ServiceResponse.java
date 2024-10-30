package fr.eni.tp_article;

public class ServiceResponse<T> {

	public String code;
	public String message;
	public T data;
	
	/**
	 * Fonction utilitaire qui permet de préparer 
	 * une réponse métier selon des paramaètres
	 * @param code Le code métier
	 * @param message Le message
	 * @param data La donnée
	 * @return
	 */
	public static <T> ServiceResponse<T> buildReponse(String code, String message, T data) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<T>();
		serviceResponse.code = code;
		serviceResponse.message = message;
		serviceResponse.data = data;
		
		// Logger
		System.out.println(String.format("Réponse : %s - %s", code, message));
		
		return serviceResponse;
	}
	
}
