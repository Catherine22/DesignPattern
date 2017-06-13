package com.catherine.intercepting_filter;

import java.util.ArrayList;
import java.util.List;

import com.catherine.intercepting_filter.member.MemberInfo;

/**
 * 拦截过滤器模式通过过滤器链处理多过滤器，和过滤器模式（{@link com.catherine.filter.criteria.Criteria}
 * ）一样自定义过滤器的接口让每个过滤的类别各自实现。
 * 
 * 原始的逻辑是Main直接通过MusicPlayer呼叫{@link #getArtist(int)}，加入拦截过滤器后变成通过MusicPlayer呼叫
 * {@link #getArtist(FilterManager, MemberInfo)}
 * 
 * @author Catherine
 *
 */
public class MusicPlayer {
	private List<Artist> artists = new ArrayList<>();

	public MusicPlayer() {
		artists.add(new Artist("Adele", Country.UK));
		artists.add(new Artist("Ed Shereen", Country.UK));
		artists.add(new Artist("Rag'n'Bone Man", Country.UK));

		artists.add(new Artist("Coldplay", Country.US));
		artists.add(new Artist("21 pilots", Country.US));
		artists.add(new Artist("Imagine Dragon", Country.US));

		artists.add(new Artist("G.E.M.", Country.CHINA));
		
		artists.add(new Artist("Vivaldi", Country.GLOBAL));
	}

	public List<String> getArtist(int country) {
		List<String> response = new ArrayList<>();
		if ((country & Country.UK) == Country.UK) {
			for (Artist a : artists) {
				if (a.country == Country.UK)
					response.add(a.name);
			}
		}
		if ((country & Country.US) == Country.US) {
			for (Artist a : artists) {
				if (a.country == Country.US)
					response.add(a.name);
			}
		}
		if ((country & Country.CHINA) == Country.CHINA) {
			for (Artist a : artists) {
				if (a.country == Country.CHINA)
					response.add(a.name);
			}
		}
		
		if ((country & Country.GLOBAL) == Country.GLOBAL) {
			for (Artist a : artists) {
				if (a.country == Country.GLOBAL)
					response.add(a.name);
			}
		}
		return response;
	}

	public List<String> getArtist(FilterManager fm, MemberInfo info) {
		return getArtist(fm.filter(info));
	}

	private class Artist {
		private String name;
		private int country;

		Artist(String name, int country) {
			this.name = name;
			this.country = country;
		}
	}
}
