package br.com.zup.controle.repository;

import br.com.zup.controle.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>  {

    Usuario findByEmail(String email);

    Usuario findByCpf(String cpf);
}
