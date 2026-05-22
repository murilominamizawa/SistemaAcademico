# SistemaAcademico
Sistema Acadêmico desktop desenvolvido em Java (Swing) integrado a banco de dados MySQL. Possui controle completo de CRUD de alunos, gerenciamento automatizado de notas/faltas com validação de duplicidade, cálculo de médias e geração dinâmica de boletins com renderização visual de status.

# 🏫 Sistema Acadêmico - UNICID

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=for-the-badge&logo=mysql)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

Um sistema desktop completo de gestão acadêmica desenvolvido em **Java** utilizando a biblioteca gráfica **Swing** para a interface de usuário, com persistência de dados em ambiente relacional **MySQL**. O projeto implementa regras rígidas de negócio estudantil, automação de consultas cadastrais e uma camada robusta de controle de histórico escolar.

---

## 🚀 Funcionalidades Principais

* **Gestão Cadastral Completa (CRUD):** Registro de alunos contemplando dados pessoais estruturados, validação de chaves duplicadas (RGM) e encerramento em cascata no banco de dados.
* **Vínculo Inteligente na Interface:** Busca automática de dados cadastrais ativada no momento em que o campo RGM perde o foco (`FocusListener`). Se o estudante existir, seu nome, curso e período são renderizados dinamicamente em negrito na tela.
* **Lançamento Controlado de Notas e Faltas:**
    * Bloqueio contra notas inválidas (fora da faixa de `0.0` a `10.0`) e frequências negativas.
    * **Validação de Duplicidade Histórica:** Impede o operador de registrar mais de uma avaliação para a mesma disciplina no mesmo semestre de um aluno.
* **Boletim Automatizado com Feedback Visual:**
    * Cálculo automático de aprovação com base nos critérios institucionais: Nota mínima `6.0` e limite máximo de `15` faltas (`MAX_FALTAS = 15`).
    * Renderização customizada de células (`DefaultTableCellRenderer`) que colore as linhas da tabela em **Verde Claro** para aprovações e **Vermelho Claro** para reprovações por nota ou falta.
    * Exibição do resumo de desempenho acadêmico com cor adaptativa de acordo com a média geral obtida.

---

## 📐 Estrutura do Projeto (Padrão de Arquitetura)

O código foi arquitetado seguindo o padrão focado na separação estrita de responsabilidades:

```text
src/br/projeto/academico/
├── Aluno.java             # Camada Model: Entidade representando o estudante
├── AlunoDAO.java          # Camada Persistence: CRUD e comandos SQL para a tabela alunos
├── NotaFalta.java         # Camada Model: Entidade representando o histórico escolar
├── NotaFaltaDAO.java      # Camada Persistence: Controle de persistência e listas de boletins
├── ConexaoBanco.java      # Camada Infrastructure: Carregamento do Driver JDBC e conexões
├── JanelaInicial.java     # Camada View: Componentes gráficos, listeners e regras visuais
└── Teste.java             # Utility: Classe isolada para diagnóstico rápido de conectividade
