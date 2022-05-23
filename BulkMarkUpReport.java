package com.wallet.zokudo.entities;

import javax.persistence.*;

import com.wallet.zokudo.enums.ChannelType;
import com.wallet.zokudo.enums.ProgramPlans;
import com.wallet.zokudo.enums.Status;

import lombok.Data;

@Entity
@Table(name="bulk_markup_report")
@Data
public class BulkMarkUpReport extends AbstractEntity{

	@Column(name = "channel", nullable = false)
	private ChannelType channel;

	@Column(name = "mcc_code", nullable = false)
	private String mccCode;

	@Column(name = "program_type", nullable = false)
	private String programType;
	
	@Column(name = "program_plan", nullable = false)
	private ProgramPlans programPlan;

	@Column(name = "fail_reason")
	private String failReason;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
}
