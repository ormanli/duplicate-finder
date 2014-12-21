/**
 * This class is generated by jOOQ
 */
package com.ormanli.duplicatefinder.db.public_.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Files extends org.jooq.impl.TableImpl<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord> {

	private static final long serialVersionUID = 2083408675;

	/**
	 * The reference instance of <code>PUBLIC.FILES</code>
	 */
	public static final com.ormanli.duplicatefinder.db.public_.tables.Files FILES = new com.ormanli.duplicatefinder.db.public_.tables.Files();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord> getRecordType() {
		return com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord.class;
	}

	/**
	 * The column <code>PUBLIC.FILES.FILE</code>.
	 */
	public final org.jooq.TableField<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord, java.lang.String> FILE = createField("FILE", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * The column <code>PUBLIC.FILES.PATH</code>.
	 */
	public final org.jooq.TableField<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord, java.lang.String> PATH = createField("PATH", org.jooq.impl.SQLDataType.VARCHAR.length(2147483647), this, "");

	/**
	 * Create a <code>PUBLIC.FILES</code> table reference
	 */
	public Files() {
		this("FILES", null);
	}

	/**
	 * Create an aliased <code>PUBLIC.FILES</code> table reference
	 */
	public Files(java.lang.String alias) {
		this(alias, com.ormanli.duplicatefinder.db.public_.tables.Files.FILES);
	}

	private Files(java.lang.String alias, org.jooq.Table<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord> aliased) {
		this(alias, aliased, null);
	}

	private Files(java.lang.String alias, org.jooq.Table<com.ormanli.duplicatefinder.db.public_.tables.records.FilesRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, com.ormanli.duplicatefinder.db.public_.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.ormanli.duplicatefinder.db.public_.tables.Files as(java.lang.String alias) {
		return new com.ormanli.duplicatefinder.db.public_.tables.Files(alias, this);
	}

	/**
	 * Rename this table
	 */
	public com.ormanli.duplicatefinder.db.public_.tables.Files rename(java.lang.String name) {
		return new com.ormanli.duplicatefinder.db.public_.tables.Files(name, null);
	}
}
