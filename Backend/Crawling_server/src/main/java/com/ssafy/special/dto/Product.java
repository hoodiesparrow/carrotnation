package com.ssafy.special.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
	private String name;// ǰ��
	private String title;// ����
	private long price;// ����
	private LocalDateTime date;// �ۼ����� (��, ��, ��)
	private String time;// ��� ��
	private String link;// ���� �Խñ� ��ũ
	private String img;// �̹����� ��� ����?
	private String location;// ����
}