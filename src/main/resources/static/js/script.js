const deleteConfirmModal = document.getElementById('deleteConfirmModal');
const pizzaNameToDeleteSpan = document.getElementById('pizzaNameToDelete');
const deletePizzaForm = document.getElementById('deletePizzaForm');

// Ascolta l'evento 'show.bs.modal' che viene attivato da Bootstrap
if (deleteConfirmModal) { 
    deleteConfirmModal.addEventListener('show.bs.modal', function(event) {
        const button = event.relatedTarget; 
        const pizzaId = button.getAttribute('data-pizza-id');
        const pizzaName = button.getAttribute('data-pizza-name');

        if (pizzaNameToDeleteSpan) {
            pizzaNameToDeleteSpan.textContent = pizzaName;
        }

        if (deletePizzaForm) {
            deletePizzaForm.action = '/pizze/delete/' + pizzaId;
        }
    });
} else {
    console.error("Errore: la modale di conferma eliminazione non è stata trovata nell'HTML.");
}