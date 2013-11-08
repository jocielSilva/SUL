package br.com.cast.enums;

public enum EnumSexo {
	
	MASCULINO("Masculino", "M"), 
	FEMININO("Feminino", "F");

	private String descricao;
	private String codigo;

	private EnumSexo(String descricao, String codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public static String recuperaDescricao(String codigo) {
		String descricao;
		if(codigo.equals("M")){
				
			descricao =  EnumSexo.MASCULINO.getDescricao();
		}
		else {
			
			descricao =  EnumSexo.FEMININO.getDescricao();
		}
		return descricao;
	}


}
