package com.clone.wanted.Company;

import com.clone.wanted.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_Id")
	private Long companyId;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;

	@Column(name = "companyName", length = 100)
	private String companyName;

	@Column(name = "region", length = 100)
	private String region;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "companyExplanation", length = 255)
	private String companyExplanation;
}