/**
 * 
 */

//Con clase
ciudad = new class Ciudad {

	constructor() {
		this.propiedad1 = null;
	}
	
	estatusFormatter(value, row, index) {
		this.propiedad1 = row;
		console.log(this.propiedad1);
		return (value === 'AC') ? 'Activo' : 'Inactivo';
	}

};

//Funcion
function estatusFormatter(value, row, index) {
	return (value === 'AC') ? 'Activo' : 'Inactivo'; // Operador condiconal ternario
}