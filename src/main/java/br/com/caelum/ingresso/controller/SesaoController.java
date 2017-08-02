package br.com.caelum.ingresso.controller;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SesaoController {
	@Autowired
	private SalaDao salaDao;
	
	@Autowired
	private FilmeDao filmeDao;
	
	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form){
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);
		return modelAndView;
	}
	public class SessaoForm{
		private Integer id;
		
		@NotNull
		private Integer salaId;
		
		@DateTimeFormat(pattern="HH:mm")
		@NotNull
		private LocalTime horario;
		
		@NotNull
		private Integer filmeId;
		
		public Sessao toSessao(SalaDao salaDao, FilmeDao fileDao){
			Filme filme = fileDao.findOne(filmeId);
			Sala sala = salaDao.findOne(salaId);
			
			Sessao sessao = new Sessao(horario, filme, sala);
			sessao.setId(id);
			
			return sessao;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getSalaId() {
			return salaId;
		}

		public void setSalaId(Integer salaId) {
			this.salaId = salaId;
		}

		public LocalTime getHorario() {
			return horario;
		}

		public void setHorario(LocalTime horario) {
			this.horario = horario;
		}

		public Integer getFilmeId() {
			return filmeId;
		}

		public void setFilmeId(Integer filmeId) {
			this.filmeId = filmeId;
		}
	}
}
